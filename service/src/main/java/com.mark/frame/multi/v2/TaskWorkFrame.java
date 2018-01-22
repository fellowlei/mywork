package com.mark.frame.multi.v2;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lulei on 2018/1/22.
 */
public abstract class TaskWorkFrame<T> implements TaskWork<T> {
    public static final ExecutorService scheduledExecutorService = Executors.newFixedThreadPool(4);


    @Override
    public void doAllTask() {
        List<T> taskList = getTaskList();
        final CountDownLatch countDownLatch = new CountDownLatch(taskList.size());
        for (final T t : taskList) {
            scheduledExecutorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        doTask(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });

        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
