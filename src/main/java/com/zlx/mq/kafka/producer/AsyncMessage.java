package com.zlx.mq.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 单向消息
 */
public class AsyncMessage {
    public static void main(String[] args) {
        // 0 配置
        Properties properties = new Properties();

        // 1 连接kafka
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "jing.tk:50017");

        // 2 指定key value序列化类型  二者等价
//        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.ehcache.impl.serialization.StringSerializer");
        // key
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // value
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        final KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<>("test-topic", i+" me"));
        }

        producer.close();

    }
}
