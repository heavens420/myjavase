package com.zlx.mq.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Component;

/**
 * 消息过滤，可通过指定tag消费特定消息或者  通过sql表达式消费特定消息
 */

@Component
@Slf4j
public class Nine {

    private final static String NAMESRV_ADDR = "192.168.123.205:9876";

    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        createMessageWithSql();
    }

    public static void createMessageWithTag() {
        // reference One  Two   Three ...
    }

    public static void createMessageWithSql() throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("unique_group9");
        producer.setNamesrvAddr(NAMESRV_ADDR);
        producer.start();

        for (int i = 0; i < 10; i++) {
            Message message = new Message("topic9", "Tag" + i, "key9", ("Hello world" + i).getBytes());

            // 设置消息标识， 消费方通过i的值判断是否需要消费
            message.putUserProperty("i", String.valueOf(i));
            final SendResult result = producer.send(message);
            log.info("message send:{}", message);
        }
        producer.shutdown();
    }

}
