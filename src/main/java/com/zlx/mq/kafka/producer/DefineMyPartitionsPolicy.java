package com.zlx.mq.kafka.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class DefineMyPartitionsPolicy implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        String message = String.valueOf(value);

        int partition;
        if (message.contains("hello")) {
            partition = 0;
        } else if (message.contains("world")) {
            partition = 1;
        }else {
            partition = 2;
        }
        return partition;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
