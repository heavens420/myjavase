package com.zlx.mq.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


@Slf4j
public class MessageWithMyPartitionPolicy {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "jing.tk:50017");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 设置自定义分区器  自定义类路径
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.zlx.mq.kafka.producer.DefineMyPartitionsPolicy");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        List<String> list = new ArrayList<>(Arrays.asList("nihao", "hello", "world", "kdkdk"));
        for (String msg : list) {
            producer.send(new ProducerRecord<>("test-topic", msg), (recordMetadata, e) -> {
                if (e == null) {
                    log.info("topic:{},partition:{},msg:{}",recordMetadata.topic(),recordMetadata.partition(),msg);
                }
            });
        }

        producer.close();
    }
}
