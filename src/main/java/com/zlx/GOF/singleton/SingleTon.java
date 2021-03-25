package com.zlx.GOF.singleton;

public class SingleTon {

    private static SingleTon singleTon = new SingleTon();

    // 静态变量 属于类 一开始就初始化
    public static int count1;
    private static int count2 = 0;
    private static int count3 = 0;
    private int count4;

    static {
        count1 = -1;
        count3++;
        System.out.println("count1 = "+ singleTon.count1);
        System.out.println("count2 = "+singleTon.count2);
        System.out.println("count4 = "+singleTon.count4);
        System.out.println("static");
    }

//    public static int count1 ;
//    private static int count2 = 0;
//    private int count4 = 1;

    private   SingleTon() {
        count1++;
        count2++;
        count4++;
        System.out.println("construct");
        System.out.println(count1+"  "+count2+ "  "+ count4);
    }

    public static SingleTon  getInstance(){
        return singleTon;
    }

    public static void main(String[] args) {
        SingleTon singleTon = new SingleTon();
//        SingleTon singleTon = SingleTon.getInstance();
        System.out.println("count1 = "+ singleTon.count1);
        System.out.println("count2 = "+singleTon.count2);
        System.out.println("count4 = "+ singleTon.count4);
        System.out.println("count3 = "+count3);
    }
}
