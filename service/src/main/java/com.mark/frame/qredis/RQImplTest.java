package com.mark.frame.qredis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lulei on 2016/3/2.
 */
public class RQImplTest extends RQImpl {
    private static AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public List<String> getTask() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            list.add(atomicInteger.incrementAndGet() + "");
        }
        return list;
    }

    @Override
    public void doTask(String s) {
        System.out.println(Thread.currentThread() + "-" + s);
    }

    public void multiTest() {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new RQImplTest().start();
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        new RQImplTest().multiTest();
    }
}
