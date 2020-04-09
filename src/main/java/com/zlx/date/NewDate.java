package com.zlx.date;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 新日期类
 */
public class NewDate {
    public static void main(String[] args) {

        //获取当前系统时间 ZoneDateTime 可设时区 其它一致
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);

        //获取当前系统日期 不含时分秒
        System.out.println(LocalDate.now());

        //获取当前日期
        Date date = new Date();
        System.out.println(date);

        //获取当前时间毫秒数
        Long milliSeconds = Instant.now().toEpochMilli();
        System.out.println(milliSeconds);

        //毫秒转日期
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSeconds), ZoneId.systemDefault());
        System.out.println(localDateTime);

        //日期转字符串
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:ss:mm");
        String formaterTime = dateTime.format(dateTimeFormatter);
        System.out.println(formaterTime);

        //字符串转日期
        LocalDateTime parse = LocalDateTime.parse("2020-03-18 16:30:30",dateTimeFormatter);
        System.out.println(parse);


        LocalTime localTime = LocalTime.of(12,23,34);
        System.out.println(localDateTime);


        //比较两个日期差几天 几小时 几分钟 ...
        Duration time = Duration.between(localDateTime,parse);
        System.out.println(time.toDays());

        //测试证明  stream流 迭代时可以修改 迭代内容  实际上遍历可以修改对象的值 不能修改 变量（八大数据类型）的值
        List<Integer> list = Stream.of(1,2,3,4,5).collect(Collectors.toList());
        list.stream().forEach(s -> {
            s *= s;
            System.out.println(s);
        });

        for (Integer i :list
             ) {
            i *= i;
            System.out.println(i);
        }

        //此非对象 遍历不能改 迭代器可改
        int[] numbers = {1,2,3,4,5};

        //将日期 按需求格式化
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy~MM~dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(new Date()));

    }
}
