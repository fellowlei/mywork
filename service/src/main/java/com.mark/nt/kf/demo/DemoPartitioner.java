package com.mark.nt.kf.demo;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.record.InvalidRecordException;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

/**
 * Created by lulei on 2018/1/12.
 */
public class DemoPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        int numPartitions = partitionInfos.size();

        if(keyBytes == null || !(key instanceof String)){
            throw new InvalidRecordException("need key");
        }
        if(key.equals("demo")){
            // demo 分到最后一个分区
            return numPartitions;
        }
        // 其他记录分配到其他分区
        return Math.abs(Utils.murmur2(keyBytes)) % (numPartitions -1);
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
