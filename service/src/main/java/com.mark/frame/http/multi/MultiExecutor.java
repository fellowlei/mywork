package com.mark.frame.http.multi;


import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lulei on 2016/6/18.
 */
public class MultiExecutor<T extends MultiTaskJob> {
    public final ExecutorService executorService;
    //    public final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
//    public final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    private final Map<String, Object> concurrentHashMap = new ConcurrentHashMap<String, Object>();
    public static final AtomicInteger id = new AtomicInteger(0);
    public static final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });


    public static AtomicInteger count = new AtomicInteger(1);
    public int totalSize;
    private boolean multiWork = true;

    public MultiExecutor(boolean multiWork) {
        this.multiWork = multiWork;
        if (this.multiWork) {
            executorService = Executors.newFixedThreadPool(4);
        } else {
            executorService = Executors.newSingleThreadExecutor();
        }
        startCount();

    }

    public void startCount() {
        System.out.println("MultiExecutor.startCount");
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Integer pers = 100 * count.get() / totalSize;
                System.out.println(pers + "%");
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
    }

    public void invokeAll(List<T> list) {
        totalSize = list.size();
        final CountDownLatch countDownLatch = new CountDownLatch(list.size());
        for (final T job : list) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Object obj = job.doTask();
                        concurrentHashMap.put(id.getAndIncrement() + "", obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        count.incrementAndGet();
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

        System.out.println("over");
        executorService.shutdown();

        dumpResult();
    }

    private void dumpResult() {
        for (Map.Entry<String, Object> entry : concurrentHashMap.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

}
