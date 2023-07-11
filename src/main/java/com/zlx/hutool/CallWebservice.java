package com.zlx.hutool;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.webservice.SoapClient;
import com.alibaba.fastjson.JSONObject;

import jdk.internal.org.xml.sax.InputSource;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;


/**
 * 调用webservice接口(不支持java17)
 */
public class CallWebservice {

    private static final String param = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
            "                  xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"\n" +
            "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
            "\t<soapenv:Header>\n" +
            "\t\t<Esb soapenv:actor=\"\"\n" +
            "\t\t     soapenv:mustUnderstand=\"0\">\n" +
            "\t\t\t<Route>\n" +
            "\t\t\t\t<Sender>10.1104</Sender>\n" +
            "\t\t\t\t<Time>#{time}</Time>\n" +
            "\t\t\t\t<ServCode>10.1109.ProvBrandProdQuotasQuery.SynReq</ServCode>\n" +
            "\t\t\t\t<MsgId>10.1109.ProvBrandProdQuotasQuery.SynReq_#{msgId}</MsgId>\n" +
            "\t\t\t\t<AuthCode/>\n" +
            "\t\t\t\t<TransId/>\n" +
            "\t\t\t\t<AuthType/>\n" +
            "\t\t\t\t<Version/>\n" +
            "\t\t\t\t<MsgType/>\n" +
            "\t\t\t\t<CarryType>0</CarryType>\n" +
            "\t\t\t\t<ServTestFlag>1</ServTestFlag>\n" +
            "\t\t\t\t<EsbId>#{esbId}</EsbId>\n" +
            "\t\t\t</Route>\n" +
            "\t\t\t<Business>\n" +
            "\t\t\t\t<BizDomain/>\n" +
            "\t\t\t\t<BizFirstType/>\n" +
            "\t\t\t\t<BizSecType/>\n" +
            "\t\t\t</Business>\n" +
            "\t\t</Esb>\n" +
            "\t</soapenv:Header>\n" +
            "\t<soapenv:Body>\n" +
            "\t\t<ns1:queryProdQuotas xmlns:ns1=\"http://ws.cnoss.ccssoft.com\"\n" +
            "\t\t                     soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
            "\t\t\t<reqXml xsi:type=\"xsd:string\">{\"count_month\":\"#{count_month}\",\"prod_code\":\"#{prod_code}\",\"query_type\":\"1\"}</reqXml>\n" +
            "\t\t</ns1:queryProdQuotas>\n" +
            "\t</soapenv:Body>\n" +
            "</soapenv:Envelope>";

    public static void main(String[] args) {
        String newParam = param.replace("#{time}", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"))
                .replace("#{count_month}", "2023-04-01")
                .replace("#{prod_code}", "01")
                .replace("#{msgId}", Instant.now().toEpochMilli() + "")
                .replace("#{esbId}", UUID.randomUUID().toString());

        String wsdlUrl = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
        String nameSpaceURI = "http://WebXml.com.cn/";
        String methodName = "getWeatherbyCityName";

//        call2(wsdlUrl, nameSpaceURI,"getWeatherbyCityName");
        call1(wsdlUrl,nameSpaceURI,methodName,"合肥");
    }

    public static String call1(String wsdlUrl, String nameSpaceURI,String methodName,String param) {
        //wsdl文档地址
//        String wsdlUrl = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
        //创建soap客户端
        SoapClient soapClient = SoapClient.create(wsdlUrl)
                // 设置调用方法名称以及命名空间
                .setMethod(methodName, nameSpaceURI)
                // 设置参数名称及参数值
                .setParam("theCityName", param)
//                .setParam("reqXml", param)
                // 设置超时时间
                .setConnectionTimeout(15000)
                .setReadTimeout(15000)
                .timeout(15000);
        // 还有其他可以配置，根据自己的需求配置
        // 调用webservice接口
        String result = soapClient.send();
        // 打印
        System.out.println(result);
        return result;
    }
    /**
     * apache axis 调用web service接口
     * @param url
     * @param methodName
     * @param nameSpaceURI
     * @return
     */
    public static String call2(String url,String nameSpaceURI, String methodName) {
        int errCode = 0;
        JSONObject resultJson = new JSONObject();
        String result = "";
        Service service = new Service();
        Call call;
        try {
            call = (Call) service.createCall();
//            QName opAddEntry = new QName("urn:demo", "GetOnlineInfo"); //设置命名空间和需要调用的方法名
            QName opAddEntry = new QName(nameSpaceURI,methodName); //设置命名空间和需要调用的方法名
            call.setTargetEndpointAddress(url); //设置请求路径
//            call.setOperationName("GetNcgOnlineInfo"); //调用的方法名
            call.setOperationName(methodName); //调用的方法名
            call.setTimeout(15000);        //设置请求超时
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//设置返回类型
            result = (String) call.invoke(opAddEntry, new Object[]{});

        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            System.out.println("查询在线状态2:" + e.getMessage());
            errCode = 2;
        } catch (javax.xml.rpc.ServiceException e) {
            throw new RuntimeException(e);
        }
        resultJson.put("errCode", errCode);
        resultJson.put("data", result);
        System.out.println(resultJson.toJSONString());
        return resultJson.toString();
    }

//    public static String call3() throws MalformedURLException {
//        URL wsdlURL = new URL("http://localhost:8080/myservice?wsdl");
//        QName serviceName = new QName("http://example.com/", "MyWebServiceService"); // 命名空间和服务名称
//        QName portName = new QName("http://example.com/", "MyWebServicePort"); // 命名空间和端口名称
//        javax.xml.ws.Service service = javax.xml.ws.Service.create(wsdlURL, serviceName);
//
//        MyWebService proxy = service.getPort(portName, MyWebService.class); // 获取代理对象
//
//        String result = proxy.sayHello("John"); // 调用远程方法
//        System.out.println(result); // 输出结果
//    }
//    }



//    public static String callInterface(String url,String jsonInput,Node code,String servCode ,String sender,String senderCode) throws Exception{
//        StringReader sr =null;
//        InputSource is = null;
//        org.w3c.dom.Element esb = null;
//        HashMap<String, String> map = new HashMap<String,String>();
//        Date t =new Date();
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateStr=sdf.format(t);
//        HeadUtil headUtil=new HeadUtil();
//
//        String str_EomsToSqmInnDataFeedbackService=headUtil.EomsToSqmInnDataFeedbackService;
//
//        Service service = new Service();
//        Call call = (Call) service.createCall();
//
//        call.setTargetEndpointAddress(url);
//        call.setUseSOAPAction(true);
//        call.setSOAPActionURI(url);
//        //call.setMaintainSession(true);// 启用session,必需
//
//        // 登陆,调用其它方法前,必需先进行登陆
//        call.setOperationName(new javax.xml.namespace.QName("http://ws.cnoss.ccssoft.com", "queryProdQuotas"));
//
//        call.addParameter("reqXml", XMLType.XSD_STRING, ParameterMode.IN);
//        call.setReturnType(XMLType.XSD_STRING);
//
//        // 添加相应的soap头信息
//        //将字符串以流的形式存起来
//
//        sr = new StringReader(str_EomsToSqmInnDataFeedbackService);
//        is = new InputSource(sr);
//        //生成w3c element
//        esb = (org.w3c.dom.Element) DocumentBuilderFactory.newInstance()
//                .newDocumentBuilder().parse(is).getElementsByTagName("Esb").item(0);
//        call.addHeader(new SOAPHeaderElement(esb));
//
//
//        //查询所有业务字典
//
//
//        String returnXml = (String) call.invoke(new Object[] {  jsonInput });
//        return returnXml;
//
//    }
}
