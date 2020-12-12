package com.zlx.thread;

import java.util.concurrent.*;

import static java.util.concurrent.Executors.defaultThreadFactory;

/**
 * @Author Zhao LongLong
 * @Date 2020/9/16
 * @Version 1.0
 * @Desc  FutureTask
 */
public class T2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        return 1221;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        T2 t2 = new T2();
        FutureTask<Integer> task = new FutureTask(t2);
        Future<Integer> fu = (Future<Integer>) executor.submit(task);
        System.out.println(fu.get());
    }

}
