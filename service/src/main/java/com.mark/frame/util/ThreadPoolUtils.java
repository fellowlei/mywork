package com.mark.frame.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lulei on 2017/4/13.
 */
public class ThreadPoolUtils {
    public static ThreadPoolExecutor newFixedThreadPool(int corePoolSize,int ququeSize,RejectedExecutionHandler rejectedExecutionHandler){
        return new ThreadPoolExecutor(corePoolSize, corePoolSize,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(ququeSize), rejectedExecutionHandler);
    }

    public static ThreadPoolExecutor newFixedThreadPool(int corePoolSize,int ququeSize){
        return new ThreadPoolExecutor(corePoolSize, corePoolSize,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(ququeSize));
    }


    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtils.newFixedThreadPool(2,5,new ThreadPoolExecutor.DiscardPolicy());

        for(int i=0; i< 100; i++){
            final int n = i;
            int size = threadPoolExecutor.getQueue().size();
            if(size >= 5){
                System.out.println("queue size: " + size);
                continue;
            }else{
                threadPoolExecutor.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(n);
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

        }
    }

}
