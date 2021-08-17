package com.zlx.javase.big;

import java.math.BigInteger;
import java.util.StringJoiner;

public class IntegerBig {
    public static void main(String[] args) {
        StringJoiner joiner = new StringJoiner("");
        for (int i = 0; i < 100; i++) {
            joiner.add(i+"");
        }
        BigInteger integer1 = new BigInteger(joiner.toString());
        System.out.println(integer1);

        integer1 = integer1.add(new BigInteger("1"));
        System.out.println(integer1);
    }
}
