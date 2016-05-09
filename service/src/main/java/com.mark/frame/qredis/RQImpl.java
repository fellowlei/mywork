package com.mark.frame.qredis;

import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lulei on 2016/3/2.
 */
public abstract class RQImpl implements RQ<String> {

    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);
    private RProvider provider = new RProviderImpl();

    private void consumer() {
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Long len = provider.llen();
                Long size = len > 10 ? 10 : len;
                if (len > 0) {
                    for (int i = 0; i < size; i++) {
                        String val = provider.rpop();
                        if (val != null) {
                            doTask(val);
                        }

                    }
                }
            }
        }, 1, 1000, TimeUnit.MILLISECONDS);
    }

    private void producer() {
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                List<String> list = getTask();
                for (int i = 0; i < list.size(); i++) {
                    provider.lpush(list.get(i));
                }
            }
        }, 1, 1000, TimeUnit.MILLISECONDS);
    }

    void start() {
        producer();
        consumer();
    }


}
