package com.mark.nt.kf;


import kafka.producer.KeyedMessage;
import kafka.producer.Producer;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * Created by lulei on 2016/4/25.
 */
public class KafkaProducer extends Thread {
    private final Producer<Integer, String> producer;
    private final String topic;
    private final Properties props = new Properties();

    public KafkaProducer(String topic) {
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", "192.168.10.1:9092");
        producer = new Producer<Integer, String>(new ProducerConfig(props));
        this.topic = topic;
    }

    @Override
    public void run() {
        int messageNo = 1;
        while (true) {
            String messageStr = new String(("Message_" + messageNo));
            System.out.println("send: " + messageStr);
//            producer.send(new KeyedMessage<Integer,String>(topic,messageStr));
            messageNo++;
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
