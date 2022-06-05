package com.zlx.generics.demo3;

import java.util.List;

public class Common {
    public <T> void testDemo(List<? super T> dst, List<T> src) {
        for (T t : src) {
            dst.add(t);
        }
    }
}
