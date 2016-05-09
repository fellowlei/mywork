package com.mark.frame.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lulei on 2016/3/2.
 */
public class QTest extends QImpl<Integer> {
    private static AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public List<Integer> getTask() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 20; i++) {
            list.add(atomicInteger.incrementAndGet());
        }
        return list;
    }

    @Override
    public void doTask(Integer integer) {
        System.out.println(integer);
    }

    public static void main(String[] args) {
        new QTest().start();
    }
}
