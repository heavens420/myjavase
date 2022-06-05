package com.zlx.mq.kafka.event;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Instant;
import java.util.Properties;
import java.util.Random;



public class SendPerfData {
    public static void main(String[] args) {
        Properties properties = new Properties();

//        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.20.110.6:9092");
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.59.222:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        Random random = new Random();
        final String[] devNames = "莱芜二路,吴县一街,金口三路,金口一广场,伏龙山路,鱼山支街,重庆大厦,黑龙江路,十梅庵街,遵义路,湘潭街,瑞金广场,仙山街,仙山东路,仙山西大厦,白沙河路,赵红广场,机场路,民航街,长城南路,流亭立交桥,虹桥广场,长城大厦,礼阳路,风岗街,中川路,白塔广场,".split(",");
        final Integer[] values = {23,45,657,123,4324,561,56,23,45,6547,8,76,999,873};
        final int[] dataSources = {1, 0};

        for (int i = 0; i < 100; i++) {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("resDomainId", random.nextLong()*100+1);
            jsonObject.put("devName",devNames[random.nextInt(devNames.length)]);
            jsonObject.put("devId", random.nextLong() * 200 + 1);
            jsonObject.put("unit", "%");
            jsonObject.put("collDevId", random.nextLong() * 300 + 1);
            jsonObject.put("metricId", "VM.CPU.USAGE");
            jsonObject.put("value", values[random.nextInt(values.length)]);
            jsonObject.put("happenTime", Instant.now().toEpochMilli());
            jsonObject.put("alarmSource", dataSources[random.nextInt(dataSources.length)]);
            jsonObject.put("tenantId", random.nextLong() * 300 + 1);

            producer.send(new ProducerRecord<>("flink", jsonObject.toJSONString()),
                    (recordMetadata, e) -> System.out.println("content-->"+recordMetadata.toString()));
        }
        producer.close();
    }


}
