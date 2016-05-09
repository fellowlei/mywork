package com.mark.nt.kf;

/**
 * Created by lulei on 2016/4/25.
 */
public interface KafkaProperties {
    String zkConnect = "192.168.10.1:2181";
    String groupId = "grou1";
    String topic = "topic1";
    String kafkaServerURL = "192.168.10.1";
    int kafkaServerPort = 9092;
    int kafkaProducerBufferSize = 64 * 1024;
    int connectionTimeout = 20000;
    int reconnectInterval = 10000;
    String topic2 = "topic2";
    String topic3 = "topic3";
    String clientId = "SimpleConsumerDemoClient";
}
