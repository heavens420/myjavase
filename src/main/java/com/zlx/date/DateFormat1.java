package com.zlx.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 通过SimpleDateFormat 转换日期格式 线程不安全
 *
 * DateFormatUtils
 *
 * DateTimeFormatter  Java8新增 线程安全
 */

public class DateFormat1 {
    public static void main(String[] args) throws ParseException {
        Date2String();
        TimeMillis2String();
        String2Date();
        String2TimeMillis();

        Date2Calendar();
//        Calendar2String();
        Millis2Date();
    }

    //日期转字符串
    public static void Date2String(){
        Date date = new Date();
        System.out.println(date);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String format = simpleDateFormat.format(date);
        System.out.println(format);
    }

    //时间毫秒数转字符串
    public static void TimeMillis2String(){
        long ti = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(ti);
        System.out.println(format);

//        System.out.println(new Date().toString());
    }

    //字符串转日期
    public static void String2Date() throws ParseException {
        String date = "2020-09-02 12:33:22";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = simpleDateFormat.parse(date);
        System.out.println(parse);
    }

    //字符串转时间毫秒数
    public static void String2TimeMillis() throws ParseException {
        String date = "2020-09-05 12:34:44";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = simpleDateFormat.parse(date).getTime();
        System.out.println(time);
    }

    //日期转Calendar
    public static void Date2Calendar(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println(calendar);
    }

    //Calendar 转String  此方法不行
    public static void Calendar2String(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(calendar);
        System.out.println(format);
    }

    //millis 转 日期
    public static void Millis2Date(){
        long time = System.currentTimeMillis() - 1000*3600*24*1;
        Date date = new Date(time);
        System.out.println(date);
    }
}
