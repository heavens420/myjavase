package com.zlx.mq.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndTimestamp;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 消费指定时间段的消息
 */
public class ExectTime {
    public static void main(String[] args) {
        Properties properties = new Properties();

        KafkaConsumer<String, String> consumer = new KafkaConsumer(properties);

        // 获取所有分区
        Set<TopicPartition> assignment = consumer.assignment();

        while (assignment.size() == 0) {
            consumer.poll(Duration.ofSeconds(1));
            assignment = consumer.assignment();
        }

        Map<TopicPartition,Long> partition2Time = new HashMap<>();

        // 构造offset 和时间
        for (TopicPartition partition : assignment) {
            // 获取前一天日期对应的时间戳
            Long time = Instant.now().toEpochMilli() - 24 * 60 * 60 * 1000;
            // 将分区与时间存入map
            partition2Time.put(partition, time);
        }

        final Map<TopicPartition, OffsetAndTimestamp> topicPartitionOffsetAndTimestampMap = consumer.offsetsForTimes(partition2Time);

        // 消费指定日期的消息
        for (TopicPartition partition : assignment) {
            final OffsetAndTimestamp offsetAndTimestamp = topicPartitionOffsetAndTimestampMap.get(partition);
            consumer.seek(partition,offsetAndTimestamp.offset());
        }

        while (true) {
            final ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : poll) {
                System.out.println(record);
            }
        }

    }
}
