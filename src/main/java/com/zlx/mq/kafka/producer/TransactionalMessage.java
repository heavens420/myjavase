package com.zlx.mq.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 事务消息 基于kafka的幂等性 kafka的幂等性指的是：每一条消息都对应着一个pid，partition，number(number递增数字)，每次启动或重启kafka都会自动分配一个pid，所以对于同一个session，可以保证数据不会丢失和重复但重启之后就不能保证
 */

@Slf4j
public class TransactionalMessage {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.59.222:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 指定事务id  开启事务必须指定 且保证全局唯一
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "kafka_trx_1");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        // 初始化事务
        producer.initTransactions();

        //开启事务
        producer.beginTransaction();

        // 发送数据
        try {
            for (int i = 0; i < 10; i++) {
                producer.send(new ProducerRecord<>("flink", i * 1000 + ""), new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        log.info("-->{}", recordMetadata.toString());
                    }
                });
            }
            // 制造异常
//            System.out.println(1/0);
            // 提交事务
            producer.commitTransaction();
        } catch (Exception e) {
            // 停止事务
            producer.abortTransaction();
        }finally {
            // 关闭连接
            producer.close();
        }
    }
}
