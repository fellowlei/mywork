package com.mark.zk;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by lulei on 2016/2/24.
 */
public class Recipes_NoLock {
    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        for(int i=0; i<10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        countDownLatch.await();
                    }catch (Exception e){}
                    SimpleDateFormat sdf =  new SimpleDateFormat("HH:mm:ss:SSS");
                    String orderNo = sdf.format(new Date());
                    System.out.println("orderNo: " + orderNo);
                }
            }).start();
        }
        countDownLatch.countDown();
    }
}
