package com.zlx.mq.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 消费指定分区
 */
public class ConsumerDefinePartition {
    public static void main(String[] args) {
        Properties propertie = new Properties();
        propertie.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "jing.tk:50016");
        propertie.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        propertie.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        propertie.put(ConsumerConfig.GROUP_ID_CONFIG, "test-000000000001");

        final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(propertie);
        List<TopicPartition> topicPartitions = new ArrayList<>();
        // 只消费 0号分区
        topicPartitions.add(new TopicPartition("topic-00000001",0));

        consumer.assign(topicPartitions);

        while (true) {
            final ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : poll) {
                System.out.println(record);

            }
        }

    }
}
