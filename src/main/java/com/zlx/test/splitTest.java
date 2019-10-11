package com.zlx.test;

import java.util.Scanner;

public class splitTest {

    public static int sum = 0;
    public static void main(String[] args) {
//        String s = "1911-12-02";


        Scanner sc = new Scanner(System.in);
        String date = sc.next();
        String[] arr = date.split("-");

//        for (String d :arr){
//            System.out.println(d);
//        }

        Integer year = Integer.parseInt(arr[0]);
        Integer month = Integer.parseInt(arr[1])-1;//输入3月份 只能加1，2月份的正月日数 再加3月份的非正月日数
//        System.out.println(month);
        Integer day = Integer.parseInt(arr[2]);
        if ((year % 100 !=0 && year % 4 ==0) || (year % 400 == 0) && month >=2){
            sum = sum + 1 ; //如果是闰年且 月份超过或等于2月份 多加一天
        }

            switch (month){
                case 11:sum = sum + 30;
                case 10:sum = sum + 31;
                case 9:sum = sum + 30;
                case 8:sum = sum + 31;
                case 7:sum = sum + 31;
                case 6:sum = sum + 30;
                case 5:sum = sum + 31;
                case 4:sum = sum + 30;
                case 3:sum = sum + 31;
                case 2:sum = sum + 28;
                case 1:sum = sum + 31;
                case 0 : sum += 0;break;
                default:
                    System.out.println("输入错误");break;
            }
            int dayYear = sum + day;
        System.out.println(date+"是"+year+"中的第 ： "+ dayYear+" 天");

    }
}
