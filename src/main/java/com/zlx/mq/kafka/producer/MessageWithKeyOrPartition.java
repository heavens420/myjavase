package com.zlx.mq.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@Slf4j
public class MessageWithKeyOrPartition {
    public static void main(String[] args) {
        Properties properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "jing.tk:50017");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        // 指定 分区和key
        for (int i = 0; i < 3; i++) {
            producer.send(new ProducerRecord<>("test-topic", 1, "a"+i, "value" + i), (recordMetadata, e) -> {
                if (e == null) {
                    log.info("topic:{},partition:{}",recordMetadata.topic(),recordMetadata.partition());
                }
            });
            log.info("-------------------------------------------------------");
            // 指定key不指定分区
            producer.send(new ProducerRecord<>("test-topic1",  "a"+i, "value122" + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        log.info("topic:{},partition:{}",recordMetadata.topic(),recordMetadata.partition());
                    }
                }
            });
        }

        producer.close();
    }
}
