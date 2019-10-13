package com.zlx.JUC;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程的创建方式 总共有 4 种
 *  1 继承 Thread 类
 *  2 实现 Runnable 接口
 *  //3 实现 Callable接口
 *  4 通过 实现 Callable接口和创建FutureTask对象
 *  5 通过 线程池
 */
public class ThreadCreate2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> thread = new FutureTask(new MyThread());//参数可以是 Runnable类型 和 Callable类型
        new Thread(thread,"A").start();
        Integer result = thread.get();//获取Callable的返回值
        System.out.println(result);
    }
}

class MyThread implements Callable {

    @Override
    public Integer call() throws Exception {
        System.out.println("线程执行了");
        return 100;
    }
}
