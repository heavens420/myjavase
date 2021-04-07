package com.zlx.mq.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 批量发送消息
 */

@Component
@Slf4j
public class Eight {

    private final static String NAMESRV_ADDR = "192.168.123.205:9876";

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("unique_group8");
        producer.setNamesrvAddr(NAMESRV_ADDR);
        producer.start();
        List<Message> messageList = new ArrayList<>();

        Message msg1 = new Message("topic8", "TagA", "key8", "Hello world".getBytes());
        Message msg2 = new Message("topic8", "TagA", "key8", "wohenaho".getBytes());
        Message msg3 = new Message("topic8", "TagA", "key8", "nihaoa".getBytes());

        messageList.add(msg1);
        messageList.add(msg2);
        messageList.add(msg3);

        final SendResult result = producer.send(messageList);

        log.info("消息发送结果：{}",result);

        producer.shutdown();

    }
}
