package com.zlx.mq.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.checkerframework.checker.units.qual.K;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

/**
 * offset：earliest，latest(默认)
 * 指定 offset 消费
 */
public class ExectOffset {
    public static void main(String[] args) {
        Properties properties = new Properties();

        KafkaConsumer consumer = new KafkaConsumer(properties);

        consumer.subscribe(Arrays.asList("topcic1"));

        // 指定offset
        // 获取所有topic的分区
        Set<TopicPartition> assignment = consumer.assignment();

        // 确保分区分配方案已经制定完毕
        while (assignment.size() > 0) {
            consumer.poll(Duration.ofSeconds(1));
            assignment = consumer.assignment();
        }
        for (TopicPartition partition : assignment) {
            // 指定所有分区的offset为100
            consumer.seek(partition,100);
        }

        while (true) {
            final ConsumerRecords records = consumer.poll(Duration.ofSeconds(2));
            System.out.println(records);
        }
    }
}
