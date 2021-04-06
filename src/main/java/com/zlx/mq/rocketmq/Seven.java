package com.zlx.mq.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 发送延迟消息
 *      rocketmq不支持自定义消息延迟,仅支持已有选项
 *      private String messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h";
 *      从1开始 对应着18个等级
 */
public class Seven {

    private final static String NAMESRV_ADDR = "192.168.123.205:9876";

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("unique_group7");
        producer.setNamesrvAddr(NAMESRV_ADDR);
        producer.start();

        for (int i = 0; i < 10; i++) {
            Message message = new Message("topic7", "Tag7", "key7", ("Hello world "+i).getBytes());
            message.setDelayTimeLevel(4);
            producer.send(message);
            System.out.printf("发送消息:%s %n",message);
        }

        producer.shutdown();
    }
}
