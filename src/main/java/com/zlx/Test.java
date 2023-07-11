package com.zlx;

import cn.hutool.core.util.XmlUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author Zhao LongLong
 * @Date 2020-11-3
 * @Version 1.0
 * @Desc 当double类型的数据太大或者太小的时候，会自动转换为科学计数法，此时将其转化为String类型的时候 会是科学计数法形式，不是想要的形式
 * 可以通过BigDecimal类将科学计数法转化为正常形式，但是，double在自动将一个很大或很小的数转化为科学计数法的时候可能会丢失精度，
 * 这样一来，自动转化后的科学计数法形式的值将不会和转化前相等，所以当将其转化为字符串再转化回去的时候也不会相等。
 */


@Slf4j
public class Test {
    public static void main(String[] args) throws Exception {
//        testOptional();
//        testStringJoiner();
//        boolean ss = getBoolean();
//        Boolean ss2 = getBoolean();
//        System.out.println(ss);
//        System.out.println(ss2);

        // 1 = 0000 0001
        // -1 = 1000 0001
        // -2 = 1000 0010
//        System.out.println(1L << -129);
//        System.out.println(1L << -1);
//        testCast();

//        callShell();
//        String a = new String("abd");
//        String b = new String("abd".getBytes(), StandardCharsets.UTF_8);
//        System.out.println(b);
//        System.out.println(a.equals(b));
//        testJson();
//        xmlTest();
//        String format = DateUtil.format(new Date(), "yyyy-MM-01");
//        System.out.println(format);

        testXml();
    }

    public static void testXml() throws DocumentException {
        String xml = "<esb>\t<route>\t\t<time>1657591902324</time>\t\t<from>oss_iom</from>\t\t<to>oss_active</to>\t\t<msg_id>9077708450570</msg_id>\t\t<Sender>1101</Sender>\t\t<ServCode>1107.Cnet_Maanshan.AsynReq</ServCode>\t\t<EsbId>f8da30fc-0187-11ed-8777-7f0000010000</EsbId>\t</route>\t<interfacemsg>\t\t<public>\t\t\t<seq_id>0</seq_id>\t\t\t<workOrderId>9077708450570</workOrderId>\t\t\t<workType>10A</workType>\t\t\t<areaCode>555</areaCode>\t\t\t<subAreaCode>6</subAreaCode>\t\t\t<subAreaName>马鞍山</subAreaName>\t\t\t<orderCode>555810110183213</orderCode>\t\t\t<waitTouchFlag/>\t\t\t<isReservOrder>0</isReservOrder>\t\t\t<asyncINAS_flag>1</asyncINAS_flag>\t\t</public>\t\t<prodInfo>\t\t\t<prodId>24010302</prodId>\t\t\t<prodName>CDMA</prodName>\t\t\t<soTypeId>102</soTypeId>\t\t\t<soTypeName>拆机</soTypeName>\t\t\t<eventId>102</eventId>\t\t\t<eventName>拆机</eventName>\t\t\t<oldProdId/>\t\t\t<oldProdName/>\t\t\t<prodCharacters>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>000001</characterId>\t\t\t\t\t<characterName>实例状态</characterName>\t\t\t\t\t<characterValue>110000</characterValue>\t\t\t\t\t<oldCharacterValue>100000</oldCharacterValue>\t\t\t\t\t<actType>102</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>000011</characterId>\t\t\t\t\t<characterName>服务号码</characterName>\t\t\t\t\t<characterValue>18156885647</characterValue>\t\t\t\t\t<oldCharacterValue>18156885647</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>600572</characterId>\t\t\t\t\t<characterName>城乡标识</characterName>\t\t\t\t\t<characterValue>10</characterValue>\t\t\t\t\t<oldCharacterValue>10</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>000013</characterId>\t\t\t\t\t<characterName>局向标识</characterName>\t\t\t\t\t<characterValue/>\t\t\t\t\t<oldCharacterValue/>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>000002</characterId>\t\t\t\t\t<characterName>标准地址</characterName>\t\t\t\t\t<characterValue>55501</characterValue>\t\t\t\t\t<oldCharacterValue>55501</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>200528</characterId>\t\t\t\t\t<characterName>产品实例描述</characterName>\t\t\t\t\t<characterValue>810098626269，用户：王晶晶18156885647和校园宽带：95552114228一起办理拆机，合同号：1002000293691，异地销户单:P83412001657331440876818后期人像发展人自行跟踪整改，发展人编码：MASCS录音：2207120000001303</characterValue>\t\t\t\t\t<oldCharacterValue>2019.3.4加装校园宽带，上网账号：18156885647，固定密码：885647，一次一密取消，2个接入方式都勾选，用户性质为宽带E族</oldCharacterValue>\t\t\t\t\t<actType>MOD</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>000004</characterId>\t\t\t\t\t<characterName>用户性质</characterName>\t\t\t\t\t<characterValue>20010</characterValue>\t\t\t\t\t<oldCharacterValue>20010</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>200530</characterId>\t\t\t\t\t<characterName>营业区编码</characterName>\t\t\t\t\t<characterValue>316033</characterValue>\t\t\t\t\t<oldCharacterValue>316033</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>000006</characterId>\t\t\t\t\t<characterName>是否预付费</characterName>\t\t\t\t\t<characterValue>0</characterValue>\t\t\t\t\t<oldCharacterValue>0</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>000020</characterId>\t\t\t\t\t<characterName>使用人</characterName>\t\t\t\t\t<characterValue>王晶晶</characterValue>\t\t\t\t\t<oldCharacterValue>王晶晶</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>600573</characterId>\t\t\t\t\t<characterName>品牌套餐类型</characterName>\t\t\t\t\t<characterValue>0</characterValue>\t\t\t\t\t<oldCharacterValue/>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>000107</characterId>\t\t\t\t\t<characterName>发展人</characterName>\t\t\t\t\t<characterValue>王文盛</characterValue>\t\t\t\t\t<oldCharacterValue>王文盛</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>000106</characterId>\t\t\t\t\t<characterName>发展单位</characterName>\t\t\t\t\t<characterValue>100008372</characterValue>\t\t\t\t\t<oldCharacterValue>100008372</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>100000135</characterId>\t\t\t\t\t<characterName>发展人编码</characterName>\t\t\t\t\t<characterValue>MASCS</characterValue>\t\t\t\t\t<oldCharacterValue>MASCS</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>7310618</characterId>\t\t\t\t\t<characterName>发展人联系电话</characterName>\t\t\t\t\t<characterValue>15305550996</characterValue>\t\t\t\t\t<oldCharacterValue>15305550996</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>600067</characterId>\t\t\t\t\t<characterName>联系人</characterName>\t\t\t\t\t<characterValue>王晶晶</characterValue>\t\t\t\t\t<oldCharacterValue>王晶晶</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>600068</characterId>\t\t\t\t\t<characterName>联系电话</characterName>\t\t\t\t\t<characterValue>18156885647</characterValue>\t\t\t\t\t<oldCharacterValue>18156885647</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>200529</characterId>\t\t\t\t\t<characterName>97合同号</characterName>\t\t\t\t\t<characterValue>1002000293691</characterValue>\t\t\t\t\t<oldCharacterValue>1002000293691</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>EXCH_ID</characterId>\t\t\t\t\t<characterName>局向</characterName>\t\t\t\t\t<characterValue>55501</characterValue>\t\t\t\t\t<oldCharacterValue>55501</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>45822</characterId>\t\t\t\t\t<characterName>开户方式</characterName>\t\t\t\t\t<characterValue>210181</characterValue>\t\t\t\t\t<oldCharacterValue>210181</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>600381</characterId>\t\t\t\t\t<characterName>用户缴费属性</characterName>\t\t\t\t\t<characterValue>3328</characterValue>\t\t\t\t\t<oldCharacterValue>3328</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>600380</characterId>\t\t\t\t\t<characterName>市场属性</characterName>\t\t\t\t\t<characterValue>802742</characterValue>\t\t\t\t\t<oldCharacterValue>802742</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>000082</characterId>\t\t\t\t\t<characterName>PSID/IMSI号码</characterName>\t\t\t\t\t<characterValue>460037171564371</characterValue>\t\t\t\t\t<oldCharacterValue>460037171564371</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>1005</characterId>\t\t\t\t\t<characterName>IMSI_G</characterName>\t\t\t\t\t<characterValue>204047238135715</characterValue>\t\t\t\t\t<oldCharacterValue>204047238135715</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>4000005</characterId>\t\t\t\t\t<characterName>IMSI_LTE</characterName>\t\t\t\t\t<characterValue>460110246533903</characterValue>\t\t\t\t\t<oldCharacterValue>460110246533903</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>000081</characterId>\t\t\t\t\t<characterName>卡号</characterName>\t\t\t\t\t<characterValue>8986031814555146562</characterValue>\t\t\t\t\t<oldCharacterValue>8986031814555146562</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>10020121</characterId>\t\t\t\t\t<characterName>实名登记类型</characterName>\t\t\t\t\t<characterValue>10</characterValue>\t\t\t\t\t<oldCharacterValue>10</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>000080</characterId>\t\t\t\t\t<characterName>机卡分离</characterName>\t\t\t\t\t<characterValue>1</characterValue>\t\t\t\t\t<oldCharacterValue>1</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>600060</characterId>\t\t\t\t\t<characterName>弹性放号</characterName>\t\t\t\t\t<characterValue>0</characterValue>\t\t\t\t\t<oldCharacterValue>0</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>45821</characterId>\t\t\t\t\t<characterName>初始信用额度</characterName>\t\t\t\t\t<characterValue>0</characterValue>\t\t\t\t\t<oldCharacterValue>0</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>000021</characterId>\t\t\t\t\t<characterName>设备来源</characterName>\t\t\t\t\t<characterValue>46</characterValue>\t\t\t\t\t<oldCharacterValue>46</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>300002698</characterId>\t\t\t\t\t<characterName>证件类型</characterName>\t\t\t\t\t<characterValue>1</characterValue>\t\t\t\t\t<oldCharacterValue>1</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>300002699</characterId>\t\t\t\t\t<characterName>证件号码</characterName>\t\t\t\t\t<characterValue>34122119990130868X</characterValue>\t\t\t\t\t<oldCharacterValue>34122119990130868X</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>000005</characterId>\t\t\t\t\t<characterName>产品密码</characterName>\t\t\t\t\t<characterValue>EC0E99E1A2B16FFE</characterValue>\t\t\t\t\t<oldCharacterValue>EC0E99E1A2B16FFE</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>1000001</characterId>\t\t\t\t\t<characterName>预配移动号码</characterName>\t\t\t\t\t<characterValue>1</characterValue>\t\t\t\t\t<oldCharacterValue>1</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>30379</characterId>\t\t\t\t\t<characterName>卡类型</characterName>\t\t\t\t\t<characterValue>0716</characterValue>\t\t\t\t\t<oldCharacterValue>0716</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>1203549</characterId>\t\t\t\t\t<characterName>PUK1</characterName>\t\t\t\t\t<characterValue>79211303</characterValue>\t\t\t\t\t<oldCharacterValue>79211303</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>500060</characterId>\t\t\t\t\t<characterName>hrpdupp</characterName>\t\t\t\t\t<characterValue>NDYwMDM3MTcxNTY0MzcxQG15Y2RtYS5jbg==</characterValue>\t\t\t\t\t<oldCharacterValue>NDYwMDM3MTcxNTY0MzcxQG15Y2RtYS5jbg==</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>500061</characterId>\t\t\t\t\t<characterName>hrpdss</characterName>\t\t\t\t\t<characterValue>B3C3287B72D3DD4E</characterValue>\t\t\t\t\t<oldCharacterValue>B3C3287B72D3DD4E</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>42000000</characterId>\t\t\t\t\t<characterName>集团空白卡序列号</characterName>\t\t\t\t\t<characterValue>8986031814555146562</characterValue>\t\t\t\t\t<oldCharacterValue>8986031814555146562</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>1149</characterId>\t\t\t\t\t<characterName>支付卡类型</characterName>\t\t\t\t\t<characterValue>00</characterValue>\t\t\t\t\t<oldCharacterValue>00</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>4000004</characterId>\t\t\t\t\t<characterName>KI_LTE</characterName>\t\t\t\t\t<characterValue>F943BDD314D3A4A9C734042F764ECAC3</characterValue>\t\t\t\t\t<oldCharacterValue>F943BDD314D3A4A9C734042F764ECAC3</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>1004</characterId>\t\t\t\t\t<characterName>KI_G</characterName>\t\t\t\t\t<characterValue>66B9E219A909A65A1245ECFABEF438B7</characterValue>\t\t\t\t\t<oldCharacterValue>66B9E219A909A65A1245ECFABEF438B7</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>1000</characterId>\t\t\t\t\t<characterName>uimid码</characterName>\t\t\t\t\t<characterValue>80F9A76F</characterValue>\t\t\t\t\t<oldCharacterValue>80F9A76F</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>1001</characterId>\t\t\t\t\t<characterName>CDMA(akey)</characterName>\t\t\t\t\t<characterValue>7833AE484A68491F</characterValue>\t\t\t\t\t<oldCharacterValue>7833AE484A68491F</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>7308</characterId>\t\t\t\t\t<characterName>拜访地出入网</characterName>\t\t\t\t\t<characterValue>1</characterValue>\t\t\t\t\t<oldCharacterValue>1</oldCharacterValue>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>ext_system</characterId>\t\t\t\t\t<characterName>来源系统</characterName>\t\t\t\t\t<characterValue>2068</characterValue>\t\t\t\t\t<oldCharacterValue/>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t\t<prodCharacter>\t\t\t\t\t<characterId>order_proirity</characterId>\t\t\t\t\t<characterName>优先级</characterName>\t\t\t\t\t<characterValue>50</characterValue>\t\t\t\t\t<oldCharacterValue/>\t\t\t\t\t<actType>KEEP</actType>\t\t\t\t</prodCharacter>\t\t\t</prodCharacters>\t\t</prodInfo>\t\t<subproducts>\t\t\t<subproduct>\t\t\t\t<subProductId>21010600</subProductId>\t\t\t\t<subProductName>呼叫等待</subProductName>\t\t\t\t<actType>102</actType>\t\t\t\t<prodCharacters>\t\t\t\t\t<prodCharacter>\t\t\t\t\t\t<characterId>000001</characterId>\t\t\t\t\t\t<characterName>产品实例状态标识</characterName>\t\t\t\t\t\t<characterValue>110000</characterValue>\t\t\t\t\t\t<oldCharacterValue/>\t\t\t\t\t\t<actType>102</actType>\t\t\t\t\t</prodCharacter>\t\t\t\t</prodCharacters>\t\t\t</subproduct>\t\t</subproducts>\t\t<vapproducts>\t\t</vapproducts>\t\t<resInfo>\t\t\t<newRes>\t\t\t\t<nbr/>\t\t\t\t<lanNbr/>\t\t\t\t<deviceNbr/>\t\t\t\t<netDeviceNbr/>\t\t\t\t<portType/>\t\t\t\t<pri_nbr/>\t\t\t\t<nbr_ab/>\t\t\t\t<ag>1</ag>\t\t\t\t<prefix/>\t\t\t\t<password/>\t\t\t\t<exch_id/>\t\t\t\t<offi_station_id/>\t\t\t\t<l3/>\t\t\t\t<v5Id/>\t\t\t\t<netAddressCode/>\t\t\t\t<ste/>\t\t\t\t<fttRes>\t\t\t\t\t<ponType/>\t\t\t\t\t<IsVpn/>\t\t\t\t\t<IsChangeVlan/>\t\t\t\t\t<inVlan/>\t\t\t\t\t<loid/>\t\t\t\t\t<config_ip/>\t\t\t\t\t<outVlan/>\t\t\t\t\t<oltType/>\t\t\t\t\t<oltPort/>\t\t\t\t\t<oltIp/>\t\t\t\t\t<onuNum/>\t\t\t\t\t<onuPort/>\t\t\t\t\t<IsChangeIp/>\t\t\t\t\t<onuIp/>\t\t\t\t\t<onuFactory/>\t\t\t\t\t<onuType/>\t\t\t\t\t<physic_textroute/>\t\t\t\t\t<light_path_name/>\t\t\t\t\t<light_path_code/>\t\t\t\t\t<vrf/>\t\t\t\t\t<zwh_no/>\t\t\t\t\t<rd/>\t\t\t\t\t<export_rt/>\t\t\t\t\t<site/>\t\t\t\t\t<site_type/>\t\t\t\t\t<ce_add/>\t\t\t\t\t<ce_no/>\t\t\t\t\t<vpn_type/>\t\t\t\t\t<pe_ce/>\t\t\t\t\t<Private_add/>\t\t\t\t\t<phoneNum/>\t\t\t\t\t<virtualLoid/>\t\t\t\t\t<extO/>\t\t\t\t\t<extT/>\t\t\t\t\t<portSp/>\t\t\t\t\t<onuDeviceNum/>\t\t\t\t\t<onuMac/>\t\t\t\t</fttRes>\t\t\t\t<imsi/>\t\t\t\t<eid/>\t\t\t\t<ims/>\t\t\t\t<tid/>\t\t\t\t<deviceNodeNumber/>\t\t\t\t<bac_main/>\t\t\t\t<bac_bak/>\t\t\t\t<subAreaCode>555</subAreaCode>\t\t\t\t<subAreaName/>\t\t\t\t<gimsi/>\t\t\t\t<hrpdupp/>\t\t\t\t<hrpdss/>\t\t\t\t<卡类型/>\t\t\t\t<支付卡类型/>\t\t\t\t<ki/>\t\t\t\t<uimId/>\t\t\t\t<RF-UIM卡号/>\t\t\t\t<CDMA-akey/>\t\t\t\t<CDMA-akAkey/>\t\t\t\t<CDMA-isNewCard/>\t\t\t\t<卡号/>\t\t\t\t<CDMA-lteimsi/>\t\t\t\t<CDMA-lteki/>\t\t\t\t<CDMA-lteopc/>\t\t\t\t<bras_size/>\t\t\t\t<bras/>\t\t\t\t<bras_ip/>\t\t\t\t<subnet_mask/>\t\t\t\t<gateway/>\t\t\t\t<bras_manufacturer/>\t\t\t</newRes>\t\t\t<oldRes>\t\t\t\t<nbr>460037171564371</nbr>\t\t\t\t<lanNbr/>\t\t\t\t<deviceNbr/>\t\t\t\t<netDeviceNbr/>\t\t\t\t<portType/>\t\t\t\t<pri_nbr/>\t\t\t\t<nbr_ab/>\t\t\t\t<ag>1</ag>\t\t\t\t<prefix/>\t\t\t\t<password/>\t\t\t\t<exch_id/>\t\t\t\t<offi_station_id/>\t\t\t\t<l3/>\t\t\t\t<v5Id/>\t\t\t\t<netAddressCode/>\t\t\t\t<ste/>\t\t\t\t<fttRes>\t\t\t\t\t<ponType/>\t\t\t\t\t<IsVpn/>\t\t\t\t\t<inVlan/>\t\t\t\t\t<outVlan/>\t\t\t\t\t<config_ip/>\t\t\t\t\t<loid/>\t\t\t\t\t<oltType/>\t\t\t\t\t<oltPort/>\t\t\t\t\t<oltIp/>\t\t\t\t\t<onuNum/>\t\t\t\t\t<onuPort/>\t\t\t\t\t<onuIp/>\t\t\t\t\t<onuFactory/>\t\t\t\t\t<onuType/>\t\t\t\t\t<physic_textroute/>\t\t\t\t\t<light_path_name/>\t\t\t\t\t<light_path_code/>\t\t\t\t\t<vrf/>\t\t\t\t\t<zwh_no/>\t\t\t\t\t<rd/>\t\t\t\t\t<export_rt/>\t\t\t\t\t<site/>\t\t\t\t\t<site_type/>\t\t\t\t\t<ce_add/>\t\t\t\t\t<ce_no/>\t\t\t\t\t<vpn_type/>\t\t\t\t\t<pe_ce/>\t\t\t\t\t<Private_add/>\t\t\t\t\t<phoneNum/>\t\t\t\t\t<virtualLoid/>\t\t\t\t\t<extO/>\t\t\t\t\t<extT/>\t\t\t\t\t<portSp/>\t\t\t\t\t<onuDeviceNum/>\t\t\t\t\t<onuMac/>\t\t\t\t</fttRes>\t\t\t\t<imsi>460037171564371</imsi>\t\t\t\t<eid/>\t\t\t\t<IsChangeIp/>\t\t\t\t<IsChangeVlan/>\t\t\t\t<ims/>\t\t\t\t<tid/>\t\t\t\t<deviceNodeNumber/>\t\t\t\t<bac_main/>\t\t\t\t<bac_bak/>\t\t\t\t<subAreaCode>555</subAreaCode>\t\t\t\t<subAreaName>马鞍山</subAreaName>\t\t\t\t<gimsi>204,047,238,135,715</gimsi>\t\t\t\t<hrpdupp>NDYwMDM3MTcxNTY0MzcxQG15Y2RtYS5jbg==</hrpdupp>\t\t\t\t<hrpdss>B3C3287B72D3DD4E</hrpdss>\t\t\t\t<卡类型> </卡类型>\t\t\t\t<支付卡类型/>\t\t\t\t<ki>66B9E219A909A65A1245ECFABEF438B7</ki>\t\t\t\t<uimId>80F9A76F</uimId>\t\t\t\t<RF-UIM卡号/>\t\t\t\t<CDMA-akey>7833AE484A68491F</CDMA-akey>\t\t\t\t<CDMA-akAkey/>\t\t\t\t<CDMA-isNewCard/>\t\t\t\t<卡号>89860318145551465629</卡号>\t\t\t\t<CDMA-lteimsi>460110246533903</CDMA-lteimsi>\t\t\t\t<CDMA-lteki>F943BDD314D3A4A9C734042F764ECAC3</CDMA-lteki>\t\t\t\t<CDMA-lteopc>71845F64C5F35DABA920B67AAEFEFB33</CDMA-lteopc>\t\t\t\t<bras_size/>\t\t\t\t<bras/>\t\t\t\t<bras_ip/>\t\t\t\t<gateway/>\t\t\t\t<subnet_mask/>\t\t\t\t<bras_manufacturer/>\t\t\t</oldRes>\t\t\t<extend_info/>\t\t</resInfo>\t\t<cust_info>\t\t\t<cust_info code=\"CUST_NAME_NEW\" value=\"王晶晶\"/>\t\t\t<cust_info code=\"cust_idno\" value=\"34122119990130868X\"/>\t\t\t<cust_info code=\"cust_source\" value=\"5\"/>\t\t\t<cust_info code=\"CUST_CODE\" value=\"2115500178762\"/>\t\t\t<cust_info code=\"CUST_GRADE_ID\" value=\"8001\"/>\t\t\t<cust_info code=\"CUST_GRADE_NAME\" value=\"金牌服务\"/>\t\t\t<cust_info code=\"CUST_TYPE\" value=\"1100\"/>\t\t\t<cust_info code=\"CUST_TYPE_NAME\" value=\"公众\"/>\t\t\t<cust_info code=\"crm_prod_inst_id\" value=\"55555002000745653\"/>\t\t\t<cust_info code=\"crm_cust_order_code\" value=\"555812340711636\"/>\t\t\t<cust_info code=\"trace_id\" value=\"881bfe046da2468787e8df5393ecec3e\"/>\t\t</cust_info>\t</interfacemsg></esb>";
        org.dom4j.Document doc = DocumentHelper.parseText(xml);
        List<Node> soNodeList = doc.selectNodes("esb/interfacemsg");
        for (Node node : soNodeList) {
            log.info("======node:{}", node);
            List<Node> list = node.selectNodes("prodInfo/prodCharacters");
            log.info("======list size:{}", list.size());
            System.out.println("=======size:"+list.size());
        }
    }

    public static void testJson(){
        String json = "{\n" +
                "\t\"root\":{\n" +
                "\t\t\"productInstId\":1,\n" +
                "\t\t\"productName\":\"产品\",\n" +
                "\t\t\"productAttr\":[\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"code\":\"属性编码1\",\n" +
                "\t\t\t\t\"name\":\"属性名称1\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"code\":\"属性编码2\",\n" +
                "\t\t\t\t\"name\":\"属性名称2\"\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t}\n" +
                "}";

        String json2 = "{\n" +
                "\t\"root\":{\n" +
                "\t\t\"productInstId\":1,\n" +
                "\t\t\"productName\":\"产品\"\n" +
                "\t}\n" +
                "}";
        Map jsonObject = JSONObject.parseObject(json2,Map.class);
        Map root = (Map) jsonObject.get("root");
        List<Map<String, Object>> list = (List<Map<String, Object>>) root.get("productAttr");
        System.out.println(list);
    }

    public static void xmlTest() {
        String xml = "<root>\n" +
                "\t<productInstId>1</productInstId>\n" +
                "\t<productName code='prod'>产品</productName>\n" +
                "\t<productAttr>\n" +
                "\t\t<attr>\n" +
                "\t\t\t<code>属性编码1</code>\n" +
                "\t\t\t<name>属性名称1</name>\n" +
                "\t\t</attr>\n" +
                "\t\t<attr>\n" +
                "\t\t\t<code>属性编码2</code>\n" +
                "\t\t\t<name>属性名称2</name>\n" +
                "\t\t</attr>\n" +
                "\t</productAttr>\n" +
                "</root>";

        Document document = XmlUtil.parseXml(xml);
        Element rootElement = XmlUtil.getRootElement(document);

        // 获取标签对应值
        String productInstId = XmlUtil.elementText(rootElement, "productInstId");
        System.out.println(productInstId);
    }

    public static void testCast() {
        Object[] array = {Integer.MAX_VALUE * 100, Integer.MAX_VALUE / 200, Integer.MAX_VALUE / 300, new int[]{}, "999"};
        String ss = array[0].toString();
        System.out.println(ss);
        Long lon = Long.parseLong(ss);
        System.out.println(lon);
    }

    public static void testDuble() {
        Double d = 0.00040000000000000001;
        Double ds = 1000000000000000000000001000000.00;

        System.out.println(ds == ds - 1);
        Long i = Long.MAX_VALUE;
        System.out.println(i);
        Double d1 = i + d;
        String d2 = String.valueOf(d1);
//        String d3 = d2 - i;

        String s = String.valueOf(d);
        String sd = String.valueOf(ds);
        System.out.println(s + "fafafa");
        System.out.println(sd);

        System.out.println(Double.valueOf(s));

        if (s.contains("E")) {
            System.out.println("科学计数法");
            BigDecimal decimal = new BigDecimal(s);
            System.out.println(decimal.toPlainString());
        }
    }

    public static void testOptional() {
        String str = "";

        // str 非空 同样会执行 orElse()方法
        Optional.ofNullable(str).orElse(sayHello());
//        Optional.ofNullable(str).orElseThrow(new NullPointerException(""))
    }

    public static String sayHello() {
        System.out.println("hello");
        return "hello";
    }

    public static void testIf() {
//        Dictionary optional = new Hashtable<String,Action>();
//
//        optional["11"] = () ->{
//
//        };
    }

    public static void testStringJoiner() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        IntStream.range(1, 10).forEach(i -> joiner.add(i + ""));
        System.out.println(joiner.toString());
    }

    public static Boolean getBoolean() {
        return null;
    }


    public static void calculate() {
        int sum = 6989;
        int z = 0, x = 0, c = 0, v = 0, b = 0;
        while (true) {
            z++;
            if (z >= 574) {

            }
            if (z + x + c + v + b == 574) {
                System.out.println("z:" + z);
                System.out.println("x:" + x);
                System.out.println("c:" + c);
                System.out.println("v:" + v);
                System.out.println("b:" + b);
                break;
            }

        }
    }

    public static void calculating(int sum, int n) {
        int mid = sum / n;

        for (int i = 0; i < n; i++) {

        }
    }

    public static void forMap() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("dicId", "aaa");
        map.put("dicName", "钢材");
        map.put("dicDes", "钢材1号");
        list.add(map);

        map = new HashMap<>();
        map.put("dicId", "bbb");
        map.put("dicName", "钢筋");
        map.put("dicDes", "钢筋1号");
        list.add(map);
        map = new HashMap<>();
        map.put("dicId", "aaa");
        map.put("dicName", "钢筋");
        map.put("dicDes", "钢筋2号");
        list.add(map);

        map = new HashMap<>();
        map.put("dicId", "bbb");
        map.put("dicName", "河沙");
        map.put("dicDes", "河沙1号");
        list.add(map);
//        Map<String, List<Map<String, Object>>> collect = list.stream().collect(Collectors.groupingBy(this::customKey));
//        Map<String, List<Map.Entry<String, Object>>> collect = list.stream().flatMap(m -> m.entrySet().stream()).collect(Collectors.groupingBy(Map.Entry::getKey));
        Map<Object, List<Map<String, Object>>> collect = list.stream().collect(Collectors.groupingBy(x -> x.get("dicId")));
        System.out.println(collect);
    }

    public static void callShell() throws IOException {
        String bash = "" +
                "ls ";
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(bash);
        Process process = builder.start();
        InputStream inputStream = process.getInputStream();

        BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(inputStream));
        String line;
//        int read = bufferedInputStream.read(new char[1024 * 100], 0, 10000);
        while ((line = bufferedInputStream.readLine()) != null) {
            System.out.println(line);
        }
//
//        Scanner scanner = new Scanner(inputStream);
//        while (scanner.hasNextLine()) {
//            String s = scanner.nextLine();
//            System.out.println(s);
//        }
    }
}
