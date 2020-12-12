package com.zlx.mq.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;


/**
 * @Author  Zhao LongLong
 * @Date    2020/8/28
 * @Version 1.0
 * @Desc    发送异步消息
 */
public class Two {
    public static void main(String[] args) throws Exception{
        // 实例化消息生产者producer
        DefaultMQProducer producer = new DefaultMQProducer("unique_group2");
        // 设置nameServer地址
        producer.setNamesrvAddr("localhost:9876");

        //启动生产者实例
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);

        int messageCount = 10;
        // 根据消息数量 设置发送次数
        CountDownLatch2 countDownLatch2 = new CountDownLatch2(messageCount);

        for (int i = 0; i < messageCount; i++) {
            final int index = i;
            //创建消息
            Message message = new Message("topic2","tag2","key2",("message2"+i).getBytes("UTF-8"));

            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%-10d OK %s %n",index,sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.printf("%-10d Exception %s %n",index,throwable);
                    throwable.printStackTrace();
                }
            });
        }

        // 5秒不发消息 关闭连接
        countDownLatch2.await(5, TimeUnit.SECONDS);
        producer.shutdown();
    }
}
