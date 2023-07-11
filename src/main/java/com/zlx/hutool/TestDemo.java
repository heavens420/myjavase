package com.zlx.hutool;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.*;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.LineHandler;
import cn.hutool.core.io.file.Tailer;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.script.ScriptRuntimeException;
import cn.hutool.script.ScriptUtil;
import com.alibaba.excel.util.IoUtils;
import com.zlx.java8features.User;
import lombok.extern.slf4j.Slf4j;
import org.python.icu.math.MathContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.script.CompiledScript;
import javax.script.ScriptException;
import javax.sound.sampled.LineListener;
import javax.xml.namespace.QName;
import javax.xml.xpath.XPathConstants;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TestDemo {
    public static void main(String[] args) throws IOException {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
//        Long a = 0L;
//        System.out.println(a + "");
//        System.out.println((Double.valueOf("0")).intValue());
//        System.out.println(String.valueOf(null));
//        xmlTest();
        String names = "北京\n" +
                "天津\n" +
                "河北\n" +
                "山西\n" +
                "内蒙古\n" +
                "辽宁\n" +
                "吉林\n" +
                "黑龙江\n" +
                "上海\n" +
                "江苏\n" +
                "浙江\n" +
                "安徽\n" +
                "福建\n" +
                "江西\n" +
                "山东\n" +
                "河南\n" +
                "湖北\n" +
                "湖南\n" +
                "广东\n" +
                "广西\n" +
                "海南\n" +
                "重庆\n" +
                "四川\n" +
                "贵州\n" +
                "云南\n" +
                "西藏\n" +
                "陕西\n" +
                "甘肃\n" +
                "青海\n" +
                "宁夏\n" +
                "新疆\n" +
                "合计\n";
        String replace = names.replace("\n", ",");
        System.out.println(replace);

//        System.out.println(BigDecimal.ZERO);
//        BigDecimal divide = loanAmount.divide(interestRate, 2, RoundingMode.HALF_UP);
//        System.out.println(divide.toString());
    }

    /**
     * 类型转换
     */
    public static void test1() {
        // 基本转换
        int a = 1;
        String s = Convert.toStr(1);
        // 设置为空时的默认值
        String s1 = Convert.toStr(null, "fsad");

        // 数组类型转换
        int[] arr = new int[]{1, 2, 3, 4, 5};
        String[] strings = Convert.toStrArray(arr);

        // 字符转日期
        Date date = Convert.toDate("2020-09-09");

        // 数组转集合 指定集合类型
        Object[] objects = new Object[]{1, 2, 3, 4, 5};
        List<String> list = Convert.toList(String.class, objects);

        // 字符 十六进制互转
        String value = "你好啊这世界";
        String hexValue = Convert.toHex(value, CharsetUtil.CHARSET_UTF_8);
        value = Convert.hexToStr(hexValue, CharsetUtil.CHARSET_UTF_8) + "-new";

        // 编码互转
        // utf-8转gbk
        // 注意 经过测试，UTF-8编码后用GBK解码再用GBK编码后用UTF-8解码会存在某些中文转换失败的问题。
        String result = Convert.convertCharset(value, CharsetUtil.UTF_8, CharsetUtil.GBK);
        String origonal = Convert.convertCharset(result, CharsetUtil.GBK, CharsetUtil.UTF_8);

        // 时间单位转换
        long time = 3600;
        long convertTime = Convert.convertTime(time, TimeUnit.SECONDS, TimeUnit.MINUTES);

        // 金额大小写转换 最多转换
        double money = 328492423.4234;
        String chineseMoney = Convert.digitToChinese(money);

        // 数字转英文表达  数字太大时有问题
        String engMoney = Convert.numberToWord(money);

        log.info("基本转换：{}", s);
        log.info("数组类型转换：{}", strings);
        log.info("字符转日期：{}", date);
        log.info("数组转集合 指定集合类型：{}", list);
        log.info("字符串转十六进制：{}", hexValue);
        log.info("十六进制转字符串：{}", value);
        log.info("utf-8转gbk：{}", result);
        log.info("gbk转utf-8：{}", origonal);
        log.info("时间单位转换：{}", convertTime);
        log.info("数字金额转中文：{}", chineseMoney);
        log.info("数字转英文：{}", engMoney);
    }

    /**
     * 日期
     */
    public static void test2() {
        // 获取月份最后一天
        int lastDay = Month.of(Calendar.JULY).getLastDay(false);

        // 获取一天的开始和结束
        String dateStr = "2017-01-01 22:33:23";
        Date date = DateUtil.parse(dateStr);

        //一天的开始，结果：2017-01-01 00:00:00
        Date beginOfDay = DateUtil.beginOfDay(date);
        //一天的结束，结果：2017-01-01 23:59:59
        Date endOfDay = DateUtil.endOfDay(date);

        // 时间偏移
        DateTime offset = DateUtil.offset(date, DateField.DAY_OF_MONTH, -2);

        // 获取星座
        String zodiac = DateUtil.getZodiac(Month.APRIL.getValue(), 3);

        // 获取生肖
        String chineseZodiac = DateUtil.getChineseZodiac(2000);

        // 计算年龄
        int age = DateUtil.ageOfNow("2015-01-01");

        // 农历日期
        ChineseDate chineseDate = new ChineseDate(1994, 2, 23);
        // 对应的公立日期
        Date gregorianDate = chineseDate.getGregorianDate();

        log.info("数字转英文：{}", lastDay);
        log.info("一天的开始：{}", beginOfDay);
        log.info("一天的结束：{}", endOfDay);
        log.info("时间偏移后：{}", offset);
        log.info("获取星座：{}", zodiac);
        log.info("生肖：{}", chineseZodiac);
        log.info("年龄：{}", age);
        log.info("公立日期：{}", gregorianDate);

    }

    /**
     * io流
     */
    public static void test3() throws IOException {
        // 文件拷贝
        Resource resource = new ClassPathResource("hutoolIo/公司人员.png");
        BufferedOutputStream outputStream = FileUtil.getOutputStream("C:\\workplace\\mine\\myjavase\\src\\main\\resources\\hutoolIo/copy.png");
        IoUtil.copy(resource.getInputStream(), outputStream, IoUtil.DEFAULT_BUFFER_SIZE);
    }

    public static void test4() {
        File[] ls = FileUtil.ls(".");
        System.out.println(Arrays.toString(ls));

    }

    public static void test5() throws IOException {
        Tailer tailer = new Tailer(FileUtil.file("C:\\workplace\\mine\\myjavase\\word.txt"), Tailer.CONSOLE_HANDLER, 1);
        tailer.start();
    }

    public static void test6() throws IOException {
        Document xml = XmlUtil.createXml("project");
        System.out.println(xml);
    }

    /**
     * 反射
     *
     * @throws IOException
     */
    public static void test7() throws IOException {
        User user = ReflectUtil.newInstance(User.class);
        ReflectUtil.invoke(user, "setId", 9090);
        System.out.println(user);
    }


    /**
     * 文件变化监听器 配合test5使用
     */
    static class ConsoleLineHandler implements LineHandler {

        @Override
        public void handle(String s) {
            log.info(s);
        }
    }

    /**
     * xml解析
     */
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

        // 获取标签内属性
        Element productName = XmlUtil.getElement(rootElement, "productName");
        String code = productName.getAttribute("code");
        System.out.println(code);

        // 获取数组类型标签值
        Element productAttr = XmlUtil.getElement(rootElement, "productAttr");
        List<Element> attrs = XmlUtil.getElements(productAttr, "attr");
        for (Element attr : attrs) {
            String code1 = XmlUtil.elementText(attr, "code");
            String name = XmlUtil.elementText(attr, "name");
            System.out.println(code1);
            System.out.println(name);
        }

        // 直接根据路径获取
        Object byXPath = XmlUtil.getByXPath("/root/productInstId", document, XPathConstants.STRING);
        System.out.println(byXPath);

        // 通过node获取数据
        Node nodeByXPath = XmlUtil.getNodeByXPath("/root/productInstId", document);
        String textContent = nodeByXPath.getTextContent();
        System.out.println(textContent);
    }

    /**
     * 执行js脚本
     */
    public static void testJsScript() {
        CompiledScript script = ScriptUtil.compile("print('Script test!');");
        try {
            script.eval();
        } catch (ScriptException e) {
            throw new ScriptRuntimeException(e);
        }
    }
}
