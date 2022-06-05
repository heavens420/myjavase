package com.zlx.generics.demo2;


public class Common {
    public <E extends A, K extends B> K testDemo(E e, K k) {
//        K k1 = k;
        final boolean equals = k.equals(e);
        if (equals) {
            int k1 = k.hashCode();
        }
        return k;
    }
}
