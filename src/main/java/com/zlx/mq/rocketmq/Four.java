package com.zlx.mq.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @Author Zhao LongLong
 * @Date 2020/8/28
 * @Version 1.0
 * @Desc    消费消息
 *              消费信息可分为 负载均衡模式和 发布订阅模式
 *
 *              负载均衡模式：默认模式，所有消费者共同消费所有消息，即生产10，消费10
 *              发布订阅模式：每一个消费者都单独消费所有消息，即生产10，消费10*consumer_number
 */
public class Four {

    private final static String NAMESRV_ADDR = "192.168.123.205:9876";

    public static void main(String[] args) throws Exception{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("unique_group1");
        consumer.setNamesrvAddr(NAMESRV_ADDR);

        // 订阅消息 订阅topic1的所有消息   参数 topic,tag
        consumer.subscribe("topic3","*");

        // 设置消费模式  默认为负载均衡模式  可选发布订阅模式
        consumer.setMessageModel(MessageModel.BROADCASTING);

        // 注册回调处理 从broker拉取的数据
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.printf("%s Receive new Message: %s %n",list);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        System.out.println("消费者已启动");
    }
}
