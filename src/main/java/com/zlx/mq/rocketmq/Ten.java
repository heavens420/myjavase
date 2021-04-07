package com.zlx.mq.rocketmq;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Component;

/**
 * 发送事务消息
 */
@Component
@Slf4j
public class Ten {
    private final static String NAMESRV_ADDR = "192.168.123.205:9876";

    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        // 使用事务消息生产者
        TransactionMQProducer producer = new TransactionMQProducer("unique_group10");
        producer.setNamesrvAddr(NAMESRV_ADDR);
        producer.start();

        // 创建事务监听器
        TransactionListener listener = new TransactionListener() {

            // 执行本地事务
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                if (StringUtils.equals(message.getTags(),"tag1")){
                    // 如果tag1 正常发送消息
                    return LocalTransactionState.COMMIT_MESSAGE;
                }
                if (StringUtils.equals(message.getTags(),"tag2")){
                    // 如果tag2 回滚消息
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }
                // 其它 返回未知状态 自动回查状态
                return LocalTransactionState.UNKNOW;
            }

            // 执行状态回查
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                log.info("回查的消息:{}",messageExt.getTags());
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        };

        // 设置事务监听器
        producer.setTransactionListener(listener);

        for (int i = 0; i < 5; i++) {
            Message message = new Message("topic10", "tag"+i, "key10", (i+"Hello world").getBytes());
            // 使用事务送消息发送方法  null 所有消息都使用事务
            producer.sendMessageInTransaction(message,null);
        }
//        producer.shutdown();
    }
}
