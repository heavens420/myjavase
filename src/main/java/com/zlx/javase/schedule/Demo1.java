package com.zlx.javase.schedule;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Demo1 {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
                if (i % 2 == 0) {
                    int finalI = i;
                    CompletableFuture.runAsync(() -> {
                        try {
                            TimeUnit.SECONDS.sleep(3);
                            System.out.println(DateUtil.date() + "================================================" + finalI);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }else {
                    System.out.println(DateUtil.date()+"--------------------------------"+i);
                }
        }

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Scheduled(cron = "*/2 * * * * ?")
    public static void test1() {
        log.info("Testing Demo:" + DateUtil.date());
    }

    public  void test2() {

        CompletableFuture.runAsync(() -> {
            System.out.println("eeeeeeeeeee");
        });
        new Thread(() -> {
            System.out.println("ccccccccccc");
        }).start();
        int a = 1 / 0;

        System.out.println("bbbbbbbb");
    }
}
