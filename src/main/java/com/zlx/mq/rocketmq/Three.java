package com.zlx.mq.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Zhao LongLong
 * @Date 2020/8/28
 * @Version 1.0
 * @Desc  单向发送消息，不返回任何结果 只管发送 不管成不成功  多用于 日志处理
 */
public class Three {

    private final static String NAMESRV_ADDR = "192.168.123.205:9876";

    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("unique_group3");
        producer.setNamesrvAddr(NAMESRV_ADDR);

        producer.start();

        for (int i = 0; i < 10; i++) {
            Message message = new Message("topic3", "tag3", "key3", "(message3+i)".getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 单向发送消息 不返回任何结果
            producer.sendOneway(message);
        }
        producer.shutdown();
    }
}
