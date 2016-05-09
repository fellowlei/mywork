package com.mark.frame.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lulei on 2016/3/1.
 */
public class QueueTest extends QueueBaseImpl<String> {

    @Override
    public void doTask(String o) {
        System.out.println(o);
    }

    @Override
    public List<String> getTask() {
        System.out.println("QueueTest.getTask");
        List<String> resultList = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            resultList.add(i + "");
        }
        return resultList;
    }

    public static void main(String[] args) {
        final QueueTest queueTest = new QueueTest();
        queueTest.doWork();
    }


}
