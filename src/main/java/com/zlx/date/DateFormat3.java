package com.zlx.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/***
 * DateTimeFormatter
 */

public class DateFormat3 {
    public static void main(String[] args) {
        Date2String();
        Millis2String();

        Millis2DateTime();
        String2DateTime();


        Instant instant = Instant.now();
        System.out.println(instant); //2020-04-09T02:43:14.529Z

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime); //2020-04-09T10:45:42.334

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime); //2020-04-09T10:43:14.529+08:00[Asia/Shanghai]

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);  //2020-04-09

        LocalTime localTime = LocalTime.now();
        System.out.println(localTime); //10:51:12.644


    }

    //日期转字符串
    public static void Date2String(){
        ZonedDateTime date = ZonedDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String format = dateTimeFormatter.format(date);
        System.out.println(format);
    }

    public static void Millis2String(){
        long milli = Instant.now().toEpochMilli();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String format = dateTimeFormatter.format(milli);
//        System.out.println(format);
    }

    //秒或毫秒转DateTime
    public static void Millis2DateTime(){
        long second = Instant.now().getEpochSecond();
        long millis = Instant.now().toEpochMilli();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(second), ZoneId.systemDefault());
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(millis), ZoneId.systemDefault());
        System.out.println(localDateTime);
        System.out.println(zonedDateTime);
    }

    public static void String2DateTime(){
        String date = "2020-02-01 09:23:34";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        LocalDateTime localDateTime = LocalDateTime.parse(date, dateTimeFormatter);
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date,dateTimeFormatter);
        System.out.println(localDateTime);
    }
}
