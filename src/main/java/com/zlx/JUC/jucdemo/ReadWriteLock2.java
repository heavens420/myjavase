package com.zlx.JUC.jucdemo;

import com.zlx.java8features.User;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock 加读写锁
 */
@Slf4j
public class ReadWriteLock2 {
    public static void main(String[] args) throws InterruptedException {
        test();
    }

    public static void test() throws InterruptedException {
        StampedLock lock = new StampedLock();
        // 随便找个测试对象
        User user = new User(0, "");
        Runnable write = () -> {
            long writeLock = lock.writeLock();
            user.setId(user.getId() + 1);
            log.info("userId: {}",user.getId());
            lock.unlockWrite(writeLock);
        };

        Runnable read = () -> {
            long tryReadLock = lock.tryOptimisticRead();
            if (!lock.validate(tryReadLock)) {
                long readLock = lock.readLock();
                try {
                    log.info("userId: {}",user.getId());
                }finally {
                    lock.unlockRead(readLock);
                }
            }else {
                log.info("try readLock failed");
            }

        };

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 500; i++) {
            executorService.submit(write);
            executorService.submit(read);
        }

        executorService.shutdown();

        if (executorService.awaitTermination(1, TimeUnit.SECONDS)) {
            log.info("result userId: {}",user.getId());
        }
    }

}
