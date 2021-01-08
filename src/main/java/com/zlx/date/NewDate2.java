package com.zlx.date;

import lombok.val;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class NewDate2 {
    public static void main(String[] args) {
        getDate();
        getTime();
        getDateTime();
        getSecondOrMiliSecond();
        operateDate();
        formatDateTime();
        parseString();
    }

    // 获取年月日
    public static void getDate(){
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
    }

    // 获取时分秒
    public static void getTime(){
        LocalTime localTime = LocalTime.now();

        // 自定义 时分秒 毫秒
        System.out.println( LocalTime.of(12, 34, 56, 99));

        // 字符串 格式化为 时分秒
        System.out.println(LocalTime.parse("12:34:33"));

        System.out.println(localTime);
    }

    // 获取年月日 时分秒
    public static void getDateTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        // 转日期
        System.out.println(localDateTime.toLocalDate());
        //转时分秒
        System.out.println(localDateTime.toLocalTime());
        System.out.println(localDateTime);
    }

    // 获取秒  毫秒数
    public static void getSecondOrMiliSecond(){
        val now = Instant.now();
        final val epochSecond = now.getEpochSecond();
        final val milli = now.toEpochMilli();
        System.out.println(epochSecond+"---"+milli);
    }
    // 时间加减
    public static void operateDate(){
        LocalDateTime localDateTime = LocalDateTime.now();
//        LocalDateTime localDateTime = LocalDateTime.of(1900,02,20,23,10);
        localDateTime = localDateTime.minusYears(100);
        localDateTime = localDateTime.plusDays(30);
        System.out.println(localDateTime);
    }

    // 格式年月日时分秒
    public static void formatDateTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        final String format = localDateTime.format(DateTimeFormatter.BASIC_ISO_DATE);
        final String format1 = localDateTime.format(DateTimeFormatter.ISO_DATE);
        final String format2 = localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
        final String format3 = localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        final String format4 = localDateTime.format(dateTimeFormatter);
        System.out.println(format);
        System.out.println(format1);
        System.out.println(format2);
        System.out.println(format3);
        System.out.println(format4);
    }

    // 字符串转时间类型
    public static void parseString(){
        String dateTime = "2223-04-20 02:02:02";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime,dateTimeFormatter);
        System.out.println(localDateTime);
    }
}

