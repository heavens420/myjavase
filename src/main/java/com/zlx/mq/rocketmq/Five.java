package com.zlx.mq.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author Zhao LongLong
 * @Date 2020/8/28
 * @Version 1.0
 * @Desc 生产顺序消息
 *          所谓顺序生产可分为全局有序和局部有序
 *              全局有序: 即a,b,c三个订单的所有流程均有序,即每一个消息都必须有序
 *              局部有序: 即a,b,c三个订单的各自流程保证有序
 *          原理:
 *              一个topic包含多个队列,每个队列中的消息有序,但不同队列之间的消息无序(多线程处理,无法保证有序)
 *              无序消息(订单为例)的每个流程会被随机分配到不同的队列中去,从而导致一个订单的所有流程的顺序无法保证
 *              若要保证有序,则可以使一个订单的所有流程均分配到一个队列中去,从而保证一个订单的流程是有序的,这就是局部有序
 *              同理可知 全局有序,即为所有不同的流程 依然按照顺序存入同一个队列,此时,一个topic就只能有一个队列,否则无法保证全局有序
 */
public class Five {

    private final static String NAMESRV_ADDR = "192.168.123.205:9876";

    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("unique_group5");
        producer.setNamesrvAddr(NAMESRV_ADDR);

        producer.start();
        // 模拟10条数据 有顺序
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String> dateList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String date = simpleDateFormat.format(new Date(9999*9999+10000*i));
            dateList.add(date);
        }

        List<Map<String, String>> messages = messages();

        for (int i = 0; i < 10; i++) {
            String body = dateList.get(i) + messages.get(i);
            Message message = new Message("topic5","tag5","Key5",body.getBytes());

            // 参数： 消息对象，队列选择器，业务标识
            SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                // 参数 ： 队列对象，消息对象，业务标识
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    // 为什么先转String 再转 int:  因为 messages.get(i).get("orderId") 会赋值给 Object o,此时o的实际类型为String类型
                    //                          所以,必须先转为String类型才能转为int类型  直接转int则报错
                    int id = Integer.valueOf((String) o);
                    int index = id % list.size();
                    return list.get(index);
                }
            },messages.get(i).get("orderId")); // 此参数 会赋值给 o

            System.out.println(sendResult);
        }
        producer.shutdown();
    }


    /**
     * 模拟 三个订单的 多个过程
     * @return
     */
    public static List messages(){
        List<Map<String, String>> list = new ArrayList();
        Map<String,String> map1 = new LinkedHashMap<>(8);
        Map<String,String> map2 = new LinkedHashMap<>(8);
        Map<String,String> map3 = new LinkedHashMap<>(8);
        Map<String,String> map4 = new LinkedHashMap<>(8);
        Map<String,String> map5 = new LinkedHashMap<>(8);
        Map<String,String> map6 = new LinkedHashMap<>(8);
        Map<String,String> map7 = new LinkedHashMap<>(8);
        Map<String,String> map8 = new LinkedHashMap<>(8);
        Map<String,String> map9 = new LinkedHashMap<>(8);
        Map<String,String> map10 = new LinkedHashMap<>(8);


        map1.put("orderId",1001+"");
        map1.put("procedure","创建");
        map2.put("orderId",1001+"");
        map2.put("procedure","发送");
        map3.put("orderId",1001+"");
        map3.put("procedure","付款");


        map4.put("orderId",1002+"");
        map4.put("procedure","创建");
        map5.put("orderId",1002+"");
        map5.put("procedure","付款");

        map6.put("orderId",1003+"");
        map6.put("procedure","创建");
        map7.put("orderId",1003+"");
        map7.put("procedure","付款");


        map8.put("orderId",1004+"");
        map8.put("procedure","创建");
        map9.put("orderId",1004+"");
        map9.put("procedure","发送");
        map10.put("orderId",1004+"");
        map10.put("procedure","付款");

        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);
        list.add(map7);
        list.add(map8);
        list.add(map9);
        list.add(map10);

        return list;
    }


}
