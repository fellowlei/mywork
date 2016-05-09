package com.mark.frame.queue;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lulei on 2016/3/2.
 */
public abstract class QImpl<T> implements QI<T> {

    private ArrayBlockingQueue<T> blockingQueue = new ArrayBlockingQueue<T>(100);
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);

    public void consumer() {
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (!blockingQueue.isEmpty()) {
                    Integer size = blockingQueue.size() < 10 ? blockingQueue.size() : 10;
                    for (int i = 0; i < size; i++) {
                        final T t = blockingQueue.poll();
                        try {
                            scheduledThreadPoolExecutor.execute(new Runnable() {
                                @Override
                                public void run() {
                                    doTask(t);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, 1, 1000, TimeUnit.MILLISECONDS);
    }

    public void producer() {
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    if (blockingQueue.size() < scheduledThreadPoolExecutor.getCorePoolSize() * 2 && scheduledThreadPoolExecutor.getQueue().size() < scheduledThreadPoolExecutor.getCorePoolSize()) {
                        List<T> list = getTask();
                        blockingQueue.addAll(list);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1, 1000, TimeUnit.MILLISECONDS);
    }

    public void start() {
        producer();
        consumer();
    }
}
