package com.mark.frame.multi;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by lulei on 2015/12/30.
 */
public abstract class MultiWorkImpl<T> implements MultiWork<T> {
    private static Logger log = Logger.getLogger(MultiWorkImpl.class);
    private ExecutorService executorService = Executors.newFixedThreadPool(200);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
    private AtomicLong atomicLong = new AtomicLong(1);
    @Override
    public void doMultiWork() {
        log.debug("MultiWorkImpl.doMultiWork execute at: " + sdf.format(new Date()) + "   execute count:" + atomicLong.getAndAdd(1));
        List<T> workList = getWorkList();
        if(workList == null || workList.isEmpty()){ // 为空，等下次执行
            return;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(workList.size());
        beforeWork(workList);
        for (final T t : workList) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        doSingleWork(t);
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
        }

        afterWork(workList);

    }

    @Override
    public void beforeWork(List<T> t) {

    }

    @Override
    public void afterWork(List<T> t) {

    }


}
