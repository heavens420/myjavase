package com.zlx.javase.big;

import java.math.BigDecimal;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.DoubleBinaryOperator;

/**
 * DoubleAccumulator
 * LongAccumulator
 * DoubleAdder
 * LongAdde
 */
public class DecimalBig {
    public static void main(String[] args) {
        BigDecimal decimal = new BigDecimal("222.3333333");
        final BigDecimal bigDecimal = decimal.add(new BigDecimal("111.1111111"));
        System.out.printf("bigDeciaml:%s\n",bigDecimal);

        DoubleAccumulator accumulator = new DoubleAccumulator(Double::sum, 23.343);
        DoubleAccumulator accumulator2 = new DoubleAccumulator((left, right) -> left - right, 99.99);

        accumulator.accumulate(2.1);
        accumulator2.accumulate(1.11);
        System.out.printf("acc:%.3f\n",accumulator.get());
        System.out.printf("acc2:%f\n",accumulator2.get());

        LongAdder adder = new LongAdder();
        adder.add(222);
        adder.decrement();
        System.out.printf("adder:%d\n",adder.longValue());
        System.out.printf("adder:%s\n",adder);

    }
}
