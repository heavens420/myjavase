package com.zlx;

import java.math.BigDecimal;

/**
 * @Author Zhao LongLong
 * @Date 2020-11-3
 * @Version 1.0
 * @Desc    当double类型的数据太大或者太小的时候，会自动转换为科学计数法，此时将其转化为String类型的时候 会是科学计数法形式，不是想要的形式
 *          可以通过BigDecimal类将科学计数法转化为正常形式，但是，double在自动将一个很大或很小的数转化为科学计数法的时候可能会丢失精度，
 *          这样一来，自动转化后的科学计数法形式的值将不会和转化前相等，所以当将其转化为字符串再转化回去的时候也不会相等。
 */


public class Test {
    public static void main(String[] args) {
        Double d = 0.00040000000000000001;
        Double ds = 1000000000000000000000001000000.00;

        System.out.println(ds == ds - 1);
        Long i = Long.MAX_VALUE;
        System.out.println(i);
        Double d1 = i + d;
        String d2 = String.valueOf(d1);
//        String d3 = d2 - i;

        String s = String.valueOf(d);
        String sd = String.valueOf(ds);
        System.out.println(s+"fafafa");
        System.out.println(sd);

        System.out.println(Double.valueOf(s));

        if (s.contains("E")){
            System.out.println("科学计数法");
            BigDecimal decimal = new BigDecimal(s);
            System.out.println(decimal.toPlainString());
        }
    }
}
