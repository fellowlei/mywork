package com.mark.frame.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lulei on 2016/3/1.
 */
public class QueueTest2 extends QueueBaseImpl<Integer> {
    AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void doTask(Integer integer) {
        System.out.println(integer);
    }

    @Override
    public List<Integer> getTask() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            Integer num = atomicInteger.incrementAndGet();
            list.add(num);
        }
        return list;
    }

    public static void main(String[] args) {
        new QueueTest2().doWork();
    }
}
