package com.zlx.java11features;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * java11 新增Java flight recode，用于记录Java程序的运行过程，分析代码质量，包括效率，报错信息和报错定位
 *
 * 模拟报错 生成.jfr文件 导入到JMC(Java Mission Control)分析程序运行过程
 */
public class JFR {
    // no limit!
    private static BlockingQueue<byte[]> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {

        Runnable producer = () -> {
            while (true) {
                // generates 1mb of object every 10ms
                queue.offer(new byte[1 * 1024 * 1024]);
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable consumer = () -> {
            while (true) {
                try {
                    // process every 100ms
                    queue.take();
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // give a name, good for profiling
        new Thread(producer, "Producer Thread").start();
        new Thread(consumer, "Consumer Thread").start();

    }

}
