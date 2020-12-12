package com.zlx.mq.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author Zhao LongLong
 * @Date 2020/8/28
 * @Version 1.0
 * @Desc 生产顺序消息
 */
public class Five {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("unique_group4");
        producer.setNamesrvAddr("localhost:9876");

        // 模拟10条数据 有顺序
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String> list = new ArrayList<>(Arrays.asList());
        for (int i = 0; i < 10; i++) {
            String date = simpleDateFormat.format(new Date(9999*9999+10000*i));
            list.add(date);
        }

        for (int i = 0; i < 10; i++) {
            String body = list.get(i);
            Message message = new Message("topic4","tag4"+i,"Key",body.getBytes());

            SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    return list.get(((int) o));
                }
            },list.get(i));

            System.out.println(sendResult);
        }
        producer.shutdown();
    }
}
