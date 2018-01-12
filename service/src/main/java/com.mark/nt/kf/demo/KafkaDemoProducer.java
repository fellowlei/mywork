package com.mark.nt.kf.demo;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * Created by lulei on 2018/1/12.
 */
public class KafkaDemoProducer {
    static class DemoProducerCallback implements Callback{
        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if(e != null){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("","");
        properties.put("metadata.broker.list", "192.168.10.1:9092");

        Producer producer = new KafkaProducer(properties);

        ProducerRecord<String,String> record = new ProducerRecord<String, String>("topic","key","value");

        // lost
        try {
            producer.send(record);
        }catch (Exception e){
            e.printStackTrace();
        }

        // sync
        try{
            producer.send(record).get();
        }catch (Exception e){
            e.printStackTrace();
        }

        // async
        try {
            producer.send(record, new DemoProducerCallback());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
