package com.zlx.JUC.thread;


import com.alibaba.ttl.TransmittableThreadLocal;
import liquibase.pro.packaged.S;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 父子线程传值
 *
 * 1 手动设置
 * 2 线程池设置TaskDecorator
 * 3 InheritableThreadLocal
 * 4 TransmittableThreadLocal
 */
@Slf4j
public class ParentAndSonThread {
    public static void main(String[] args) throws InterruptedException {
        test1();
    }


    /**
     * 手动设置
     * @throws InterruptedException
     */
    public static void test1() throws InterruptedException {
        OauthContext.set(new LoginVal());
        LoginVal loginVal = OauthContext.get();

        log.info("父线程值：{}", OauthContext.get());
        CompletableFuture.runAsync(() -> {
            OauthContext.set(loginVal);
            log.info("子线程值：{}", OauthContext.get());
        });

        // 主线程阻塞 等待子线程完成
        Thread.currentThread().join(100);
//        TimeUnit.SECONDS.sleep(1);
    }


    /**
     * 配合线程池使用 建议使用
     */
    public static void test3() {

        // 定义线程池
   /*     @Bean("taskExecutor")
        public ThreadPoolTaskExecutor taskExecutor() {
            ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
            poolTaskExecutor.setCorePoolSize(xx);
            poolTaskExecutor.setMaxPoolSize(xx);
            // 设置线程活跃时间（秒）
            poolTaskExecutor.setKeepAliveSeconds(xx);
            // 设置队列容量
            poolTaskExecutor.setQueueCapacity(xx);
            //设置TaskDecorator，用于解决父子线程间的数据复用
            poolTaskExecutor.setTaskDecorator(new ContextTaskDecorator());
            poolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            // 等待所有任务结束后再关闭线程池
            poolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
            return poolTaskExecutor;
        }*/

        // 使用线程池
     /*   public void handlerAsync() {
            log.info("父线程的用户信息：{}", OauthContext.get());
            //执行异步任务，需要指定的线程池
            CompletableFuture.runAsync(()-> log.info("子线程的用户信息：{}", OauthContext.get()),taskExecutor);
        }*/
    }

    public static void test4() {

    }

}


/**
 * TaskDecorator简单用法
 */
class ContextTaskDecorator implements TaskDecorator{
    @Override
    public Runnable decorate(Runnable runnable) {
        //获取父线程的loginVal
        LoginVal loginVal = OauthContext.get();
        return () -> {
            try {
                // 将主线程的请求信息，设置到子线程中
                OauthContext.set(loginVal);
                // 执行子线程，这一步不要忘了
                runnable.run();
            } finally {
                // 线程结束，清空这些信息，否则可能造成内存泄漏
                OauthContext.clear();
            }
        };
    }
}

class OauthContext {
    private static  final  ThreadLocal<LoginVal> loginValThreadLocal=new ThreadLocal<>();

    public static  LoginVal get(){
        return loginValThreadLocal.get();
    }
    public static void set(LoginVal loginVal){
        loginValThreadLocal.set(loginVal);
    }
    public static void clear(){
        loginValThreadLocal.remove();
    }
}

class LoginVal{

}


/**
 * InheritableThreadLocal有缺陷 不建议使用
 */
class OauthContext3 {
    private static  final  InheritableThreadLocal<LoginVal> loginValThreadLocal=new InheritableThreadLocal<>();

    public static  LoginVal get(){
        return loginValThreadLocal.get();
    }
    public static void set(LoginVal loginVal){
        loginValThreadLocal.set(loginVal);
    }
    public static void clear(){
        loginValThreadLocal.remove();
    }
}

/**
 * TransmittableThreadLocal 建议使用
 */
class OauthContext2 {
    private static  final TransmittableThreadLocal<LoginVal> loginValThreadLocal=new TransmittableThreadLocal<>();

    public static  LoginVal get(){
        return loginValThreadLocal.get();
    }
    public static void set(LoginVal loginVal){
        loginValThreadLocal.set(loginVal);
    }
    public static void clear(){
        loginValThreadLocal.remove();
    }
}