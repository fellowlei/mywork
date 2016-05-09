package com.mark.frame.queue;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lulei on 2016/3/1.
 */
public abstract class QueueBaseImpl<T> implements QueueBase<T> {
    private ArrayBlockingQueue<T> blockingQueue = new ArrayBlockingQueue<T>(100);
    private ScheduledThreadPoolExecutor scheduledExecutorService = new ScheduledThreadPoolExecutor(5);

    public void execute() {
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (!blockingQueue.isEmpty()) {
                    for (int i = 0; i < 5; i++) {
                        final T t = blockingQueue.poll();
                        scheduledExecutorService.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    doTask(t);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }
        }, 1, 1000, TimeUnit.MILLISECONDS);
        System.out.println("QueueBaseImpl.execute");
    }

    public void addTaskJob() {
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                addTask();
            }
        }, 1, 1000, TimeUnit.MILLISECONDS);
        System.out.println("QueueBaseImpl.addTaskJob");
    }


    public void addTask() {
        if (blockingQueue.size() < 10 && scheduledExecutorService.getQueue().size() < 5) {
            List<T> list = getTask();
            if (!CollectionUtils.isEmpty(list)) {
                blockingQueue.addAll(list);
            }
        }
    }

    public void doWork() {
        addTaskJob();
        execute();
    }


}
