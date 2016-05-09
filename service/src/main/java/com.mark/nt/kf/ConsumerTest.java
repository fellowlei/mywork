package com.mark.nt.kf;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

import java.util.Properties;

/**
 * Created by lulei on 2016/4/25.
 */
public class ConsumerTest implements Runnable {
    private KafkaStream m_stream;
    private int m_threadNumber;

    public ConsumerTest(KafkaStream m_stream, int m_threadNumber) {
        this.m_stream = m_stream;
        this.m_threadNumber = m_threadNumber;
    }

    @Override
    public void run() {
        ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
        while (it.hasNext()) {
            System.out.println("Thread " + m_stream + ": " + new String(it.next().message()));
        }
        System.out.println("shutting down thread: " + m_threadNumber);
    }


}
