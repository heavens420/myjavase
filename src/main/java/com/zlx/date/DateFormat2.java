package com.zlx.date;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * DateFormatUtils
 */


public class DateFormat2 {
    public static void main(String[] args) throws ParseException {
        Calendar2String();
        Date2String();
        millis2String();

        String2Date();
        Date2Calendar();
    }


    //Calendar转字符串
    public static void Calendar2String(){
        Calendar calendar = Calendar.getInstance();
        String format = DateFormatUtils.format(calendar, "yyyy-MM-dd HH:mm:ss", TimeZone.getDefault(), Locale.CHINA);
        System.out.println(format);
    }

    //date转字符串
    public static void Date2String(){
        Date date = new Date();
        String format = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
        System.out.println(format);
    }

    //millis转字符串
    public static void millis2String(){
        long mi = System.currentTimeMillis();
        String format = DateFormatUtils.format(mi, "yyyy-MM-dd HH:mm");
        System.out.println(format);
    }

    //字符串转日期
    public static void String2Date() throws ParseException {
        String datestr = "2020-12-12 08:09:38";
        Date date = DateUtils.parseDate(datestr, "yyyy-MM-dd HH:mm:ss");
        System.out.println(date);
    }

    //Date2Calendar
    public static void Date2Calendar() throws ParseException {
        Date date = new Date();
//        String datestr = "2020-12-12 08:09:38";
//        Date date = DateUtils.parseDate(datestr, "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Calendar toCalendar = DateUtils.toCalendar(date);
        System.out.println(toCalendar);
    }
}
