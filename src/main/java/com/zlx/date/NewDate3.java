package com.zlx.date;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class NewDate3 {
    public static void main(String[] args) {
        MonthDay birthday = MonthDay.of(8, 31);
//        matchBirthday(birthday);
//        getClock();
//        compareDate();
        periodDays();
    }


    /**
     * 判断今天是不是你生日
     *
     * @param monthDay
     */
    public static void matchBirthday(MonthDay monthDay) {
        MonthDay day = MonthDay.from(LocalDate.now());
        if (day.equals(monthDay)) {
            System.out.println("match happy birthday");
        } else {
            System.out.println("not match ");
        }
    }

    /**
     * 日期时间戳
     */
    public static void getClock() {
        // Returns the current time based on your system clock and set to UTC.
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock.millis());

        // Returns time based on system clock zone
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("Clock2 : " + defaultClock.millis());

        System.out.println("TimeInstant:" + Instant.now().toEpochMilli());
    }

    /**
     * 日期大小比较
     */
    public static void compareDate() {
        LocalDate today = LocalDate.now();

//        LocalDate tomorrow = LocalDate.of(2018,2,6);
        LocalDate tomorrow = today.plusDays(1);
        if (tomorrow.isAfter(today)) {
            System.out.println("之后的日期:" + tomorrow);
        }

        LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);
        if (yesterday.isBefore(today)) {
            System.out.println("之前的日期:" + yesterday);
        }

        if (today.isEqual(today)) {
            System.out.println("今天的日期：" + today);
        }
    }

    /**
     * 计算两个日期间隔的年月日
     */
    public static void periodDays() {
        LocalDate today = LocalDate.now();

        LocalDate java8Release = LocalDate.of(2018, 12, 14);

        Period periodToNextJavaRelease = Period.between(java8Release, today);
        System.out.println("Months left between today and Java 8 release : " + periodToNextJavaRelease.getMonths());
    }


}
