package com.zlx.JUC.atomic;

import java.math.BigInteger;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

public class IntAtomic {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(5);

        // 加 n 操作  ++i
//        integer.addAndGet(2);
        // 加 n 操作  i++
        integer.getAndAdd(3);

        // 加 1 操作 i++
        integer.getAndIncrement();
        // ++i
        integer.incrementAndGet();

        // 减 1 操作 --i
//        integer.decrementAndGet();
        //  i--
//        integer.getAndDecrement();

        integer.getAndSet(33);
        integer.set(44);
        System.out.println(integer.get());

    }
}
