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
import com.alibaba.excel.util.IoUtils;
import com.zlx.java8features.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;

import javax.sound.sampled.LineListener;
import java.io.*;
import java.math.BigDecimal;
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
        System.out.println("sdfs".contains(""));
        byte[] b = new byte[]{'d','e','f'};
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStream outputStream = new ByteArrayOutputStream();

        byteArrayOutputStream.write(b);
        byteArrayOutputStream.close();
//        byteArrayOutputStream.writeTo(outputStream);
        outputStream.write(byteArrayOutputStream.toByteArray());
        System.out.println(byteArrayOutputStream);
        System.out.println(outputStream);
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
        IoUtil.copy(resource.getInputStream(),outputStream,IoUtil.DEFAULT_BUFFER_SIZE);
    }

    public static void test4() {
        File[] ls = FileUtil.ls(".");
        System.out.println(Arrays.toString(ls));

    }

    public static void test5() throws IOException {
        Tailer tailer = new Tailer(FileUtil.file("C:\\workplace\\mine\\myjavase\\word.txt"),Tailer.CONSOLE_HANDLER,1);
        tailer.start();
    }

    public static void test6() throws IOException {
        Document xml = XmlUtil.createXml("project");
        System.out.println(xml);
    }

    /**
     * 反射
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
}
