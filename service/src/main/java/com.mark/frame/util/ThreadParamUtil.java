package com.mark.frame.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lulei on 2017/3/10.
 */
public class ThreadParamUtil {
   public static  ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public static String get(){
        return threadLocal.get();
    }

    public static void set(String obj){
        threadLocal.set(obj);
    }

    public static void test(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for(int i=0; i<3; i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 3; j++) {
                        ThreadParamUtil.set(j + "");
                        System.out.println(Thread.currentThread().getName() + " " +ThreadParamUtil.get());
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

    }

    public static void main(String[] args) {
            test();
    }

}
