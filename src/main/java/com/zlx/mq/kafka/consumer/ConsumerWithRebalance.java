package com.zlx.mq.kafka.consumer;


import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

/**
 *  消费平衡策略
 *  分为：range，RoundRobin,sticky
 *  默认为range
 */
public class ConsumerWithRebalance {
    public static void main(String[] args) {

        Properties properties = new Properties();
        // 设置分区策略
        properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, "org.apache.kafka.clients.consumer.RoundRobinAssignor");
    }
}
