package com.mark.nt.kf.demo;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by lulei on 2018/1/12.
 */
public class KafkaDemoConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "false");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(props);

        consumer.subscribe(Collections.singletonList("topic"));

        Map<String,Integer> countMap =new HashMap<String, Integer>();
        try{
            while(true){
                ConsumerRecords<String, String> records = consumer.poll(100);
                for(ConsumerRecord<String,String> record: records){
                    System.out.println(record.topic() + ":" + record.partition() + ":" + record.offset());
                    System.out.println(record.key() + ":" + record.value());

                    int count = 1;
                    if(countMap.containsValue(record.value())){
                        count = countMap.get(record.value()) + 1;
                    }
                    countMap.put(record.value(),count);
                    System.out.println(JSON.toJSONString(countMap));
                }
            }
        }catch (Exception e){
            consumer.close();
        }


    }
}
