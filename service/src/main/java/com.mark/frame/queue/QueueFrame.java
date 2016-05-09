package com.mark.frame.queue;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lulei on 2016/3/1.
 */
public abstract class QueueFrame<T> implements QueueBaseI<T> {
    private ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(10);
    private ArrayBlockingQueue<T> blockingQueue = new ArrayBlockingQueue<T>(100);


    /**
     * 消费线程
     */
    public void consumer() {
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (!blockingQueue.isEmpty()) {
                    for (int i = 0; i < executorService.getCorePoolSize(); i++) {
                        final T t = blockingQueue.poll();
                        try {
                            executorService.execute(new Runnable() {
                                @Override
                                public void run() {
                                    doTask(t);
                                }
                            });
                        } catch (Exception e) {

                        }
                    }
                }

            }
        }, 1, 1000, TimeUnit.MILLISECONDS);
    }

    /**
     * 生成线程
     */
    public void producer() {
        if (blockingQueue.size() < executorService.getCorePoolSize() * 2 || executorService.getQueue().size() < executorService.getCorePoolSize()) {
            try {
                List<T> list = getTask();
                if (!CollectionUtils.isEmpty(list)) {
                    blockingQueue.addAll(list);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void start() {
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                producer();
            }
        }, 1, 1000, TimeUnit.MILLISECONDS);

        consumer();
    }
}
