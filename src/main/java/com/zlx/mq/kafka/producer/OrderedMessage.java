package com.zlx.mq.kafka.producer;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 发送有序数据：
 *      1  设置max.in.flight.requests.per.connection=1（不需要考虑是否开启幂等性）
 *          原理：所有数据都发送到一个分区，即可保证数据有序
 *      2  max.in.flight.requests.per.connection 需要设置小于等于5(需要开启幂等性,默认开启)
 *          原理：kafka服务端会开启一个缓存，对最多5次请求进行缓存并排序后有序发出
 */

@Slf4j
public class OrderedMessage {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.59.222:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 设置缓存请求次数
        properties.put("max.in.flight.requests.per.connection", 5);

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        producer.send(new ProducerRecord<>("flink", "hahaha"));
    }
}
