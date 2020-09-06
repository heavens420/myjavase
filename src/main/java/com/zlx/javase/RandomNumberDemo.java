package com.zlx.javase;

import java.util.Random;

public class RandomNumberDemo {
    public static void main(String[] args) {
        Random s = new Random();
        Random d = new Random(100);
        for (int i = 0; i < 5; i++) {
            int a = s.nextInt();
            int b = d.nextInt();
            System.out.print(a+"\t");
            System.out.print(b+"\t");
        }
        System.out.println();
        for (int i = 0; i < 5; i++) {  //区间 左闭右开
            int a = s.nextInt(2);
            int b = d.nextInt(100);
            System.out.print(a+"\t");
            System.out.print(b+"\t");
        }
        System.out.println();
        for (int i = 0; i < 5; i++) {//double 类型 左闭右开
            int f = (int)(Math.random()*99)+1;
            System.out.print(f+"\t");
        }


        System.out.println();
        for (int i = 0; i < 5; i++) {
            long g = System.currentTimeMillis();
            int h = (int) (g%99+1);
            System.out.print(h+"\t");
        }
    }
}
