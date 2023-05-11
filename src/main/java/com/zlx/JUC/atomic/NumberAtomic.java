package com.zlx.JUC.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

@Slf4j
public class NumberAtomic {
    public static void main(String[] args) throws InterruptedException {
//        test2();
//        test3();
//        test4();
    }

    /**
     * AtomicInteger 做加减法
     */
    public static void tes1(){
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

    /**
     * LongAdder 做累加 初始只能为0
     * @throws InterruptedException
     */
    public static void tes2() throws InterruptedException {
        LongAdder longAdder = new LongAdder();
        ExecutorService pool = Executors.newFixedThreadPool(50);
        Runnable t1 = () -> longAdder.add(1L);

        for (int i = 0; i < 500; i++) {
            Future<?> submit = pool.submit(t1);
//            submit.isDone();
        }
        pool.shutdown();

        if (pool.awaitTermination(1000, TimeUnit.MICROSECONDS)) {
            log.info("sum:{}", longAdder.longValue());
        }
    }

    /**
     * LongAccumulator 增强版的LongAdder
     * @throws InterruptedException
     */
    public static void tes3() throws InterruptedException {
//        LongAccumulator accumulator = new LongAccumulator(Long::sum,0);
        LongAccumulator accumulator = new LongAccumulator(new LongBinaryOperator() {
            @Override
            public long applyAsLong(long left, long right) {
                return left - right;
            }
        }, 500);
        ExecutorService executors = Executors.newFixedThreadPool(10);
        Runnable t = () -> accumulator.accumulate(1L);

        for (int i = 0; i < 500; i++) {
            executors.submit(t);
        }

        executors.shutdown();

        if (executors.awaitTermination(1, TimeUnit.SECONDS)) {
            log.info("sum:{}", accumulator.get());
        }
    }

    public static void test4() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 计算阶乘
//        LongAccumulator accumulator = new LongAccumulator((x, y) -> x * y,1);

        // 计算累加和
//        LongAccumulator accumulator = new LongAccumulator((x, y) -> x + y,0);

        // 做减法就有线程安全问题 why？
//        LongAccumulator accumulator = new LongAccumulator((x, y) -> x - y,0);

        // 最值
        LongAccumulator accumulator = new LongAccumulator((x, y) -> Math.min(x,y),0);


        IntStream.range(1, 500).forEach(i -> executorService.submit(() -> accumulator.accumulate(i)));

        executorService.shutdown();
        if (executorService.awaitTermination(10, TimeUnit.SECONDS)) {
            log.info("sum:{}", accumulator.get());
        }
    }
}
