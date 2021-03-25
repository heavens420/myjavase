package com.zlx.thread;

import java.util.concurrent.*;

import static java.util.concurrent.Executors.*;

/**
 * @Author Zhao LongLong
 * @Date 2020/9/16
 * @Version 1.0
 * @Desc  Future
 */
public class T1 implements Callable {
    @Override
    public Object call() throws Exception {
        for (int i = 0; i < 10000000; i++) {
            System.out.println(i);
        }
        return "success";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        T1 t1 = new T1();
        // 自动创建线程池
        // ExecutorService executorService = Executors.newCachedThreadPool();
        // 手动创建线程池  避免资源耗尽风险
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        // 获取线程的返回值
        Future submit = executor.submit(t1);
        final Object result = submit.get();
        System.out.println(result);
        executor.shutdown();

        // 尝试取消执行的线程  但是只是尝试  不一定成功
        boolean cancel = submit.cancel(true);
        boolean cancelled = submit.isCancelled();
        boolean done = submit.isDone();

        System.out.println(cancel+"<--是否取消线程执行||取消是否成功-->"+cancelled+"\n线程是否执行完成"+done);

    }

}
