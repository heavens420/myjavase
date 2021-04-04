package com.zlx.mq.rocketmq;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 *  顺序消费消息
 */
public class Six {

    private final static String NAMESRV_ADDR = "192.168.123.205:9876";

    public static void main(String[] args) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("unique_group6");

        consumer.setNamesrvAddr(NAMESRV_ADDR);

        consumer.subscribe("topic5","*");

        consumer.registerMessageListener(new MessageListenerOrderly() {
            @SneakyThrows
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                for (MessageExt msg: list) {
                    String result = new String(msg.getBody());
                    int id = msg.getQueueId();
                    System.out.printf("线程名称：%s ,队列ID: %d, 顺序消息详情: %s %n",Thread.currentThread().getName(),id, result);
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();
        System.out.println("消费者启动成功");
    }
}
