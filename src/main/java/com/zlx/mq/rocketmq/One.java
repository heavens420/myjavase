package com.zlx.mq.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * @Author      Zhao LongLong
 * @Date        2020/8/28
 * @Version     1.0
 * @Desc        Producer端发送 同步消息
 *
 *              消息发送 三种方式：
 *                  同步发送：
 *                  异步发送：
 *                  单向发送：
 */
@Slf4j
@Component
public class One {

        private final static String NAMESRV_ADDR = "192.168.123.205:9876";
//    private final static String NAMESRV_ADDR = "175.24.188.154:9876";

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {

        // 实例化消费生产者
        DefaultMQProducer producer = new DefaultMQProducer("unique_group1");

        // 设置nameServer地址
        producer.setNamesrvAddr(NAMESRV_ADDR);

        // 启动producer实例
        producer.start();

        for (int i = 0; i < 10; i++) {
            // 创建消息 指定topic和tag和消息内容
            Message message = new Message("topic1", "tag1", "KEY_1",("message1" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));

            // 发送消息到一个broker
            SendResult result = producer.send(message);

            // 消息发送状态
            final SendStatus status = result.getSendStatus();

            // 消息ID
            final String msgId = result.getMsgId();

            //消息队列id
            final int queueId = result.getMessageQueue().getQueueId();

            // 查看返回消息
//            System.out.printf("%s%n",result);
            log.info("消息发送状态:{},消息ID:{},消息队列ID:{}",status,msgId,queueId);

            TimeUnit.SECONDS.sleep(1);
        }
        // 发送结束 关闭producer实例
        producer.shutdown();
    }
}
