package com.zlx.JUC;

import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;


/**
 * ForkJoin 拆分计算求和
 */
public class ForkJoinTest extends RecursiveTask<Long> {

    // 拆分任务足够大  否则拆分需要的时间比计算还多 得不偿失
    private final int holdSize = 500000;
    private long first;
    private long last;

    public ForkJoinTest(long first, long last) {
        this.first = first;
        this.last = last;
    }


    @Override
    protected Long compute() {
        long result = 0L;
        if (last - first <= holdSize) {
            for (long i = first; i <= last; i++) {
                result += i;
            }
        } else {
            long middle = first + (last - first) / 2;
            ForkJoinTest left = new ForkJoinTest(first, middle);
            ForkJoinTest right = new ForkJoinTest(middle + 1, last);
            left.fork();
            right.fork();
            result = left.join() + right.join();
        }
        return result;
    }

    public static Long get_sum(long start, long end) {
        Long sum = 0L;
        for (long i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start = Instant.now().toEpochMilli();
//        System.out.println(get_sum(0L, 91000000000L));
        long over = Instant.now().toEpochMilli();
        System.out.println(over - start);


        long now = Instant.now().toEpochMilli();
        ForkJoinTask<Long> forkJoinTask = new ForkJoinTest(0L, 91000000000L);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        final ForkJoinTask<Long> sum = forkJoinPool.submit(forkJoinTask);
        System.out.println(sum.get());
        long end = Instant.now().toEpochMilli();
        System.out.println(end - now);

    }
}
