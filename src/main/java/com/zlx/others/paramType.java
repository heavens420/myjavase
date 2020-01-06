package com.zlx.others;

public class paramType {
    public static void main(String[] args) {
        short s = 1;
        s++;
        s += 1;
        //以上 两句相当于
        s = (short)(s + 1);
        //即 ： ++ 操作 和 += 操作 自动隐式向下转型
//        s = s + 1; //此句报错

        System.out.println(getType(s));
    }

    //获取变量类型
    public static String getType(Object o){
        return o.getClass().toString();
    }
}