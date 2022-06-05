package com.zlx.mq.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * 异步提交offset 消费消息
 */
public class AsyncCommint {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "jing.tk:50016");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-0000001");
        // 关闭自动提交
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, false);

        KafkaConsumer<String, String> consumer = new KafkaConsumer(properties);

        consumer.subscribe(Arrays.asList("topic1", "topic2"));


        while (true) {
            final ConsumerRecords<String,String> poll = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : poll) {
                System.out.println(record);
            }
            //  异步提交方式
            consumer.commitSync();
        }
    }
}
