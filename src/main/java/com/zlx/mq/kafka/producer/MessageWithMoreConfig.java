package com.zlx.mq.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@Slf4j
public class MessageWithMoreConfig {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.59.222:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 设置缓冲区大小 默认32M
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 128*1024*1024);

        // 批次大小 默认16k
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 128 * 1024);

        // linger.ms  默认0毫秒 即有数据就发送
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 10);

        // 设置压缩方式
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");

        // 设置ack 0 1 -1 ，all all与 -1 等价
        properties.put(ProducerConfig.ACKS_CONFIG, "all");

        //设置重试次数  默认MAX_INTEGER
        properties.put(ProducerConfig.RETRIES_CONFIG, 1000);

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<>("flink", i * 1000 + ""), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    log.info("-->{}", recordMetadata.toString());
                }
            });
        }
        producer.close();
    }
}
