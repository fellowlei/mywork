package com.mark.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by lulei on 2016/2/24.
 */
public class Recipes_Lock {
    static String lock_path = "/curator_lock_path";
    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.10.1:2181").retryPolicy(new ExponentialBackoffRetry(1000,3)).build();

    public static void main(String[] args) {
        client.start();

        final InterProcessMutex lock = new InterProcessMutex(client,lock_path);
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        for(int i=0; i<30; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        countDownLatch.await();
                        lock.acquire();
                    }catch (Exception e){}

                    SimpleDateFormat sdf =  new SimpleDateFormat("HH:mm:ss:SSS");
                    String orderNo = sdf.format(new Date());
                    System.out.println("orderNo: " + orderNo);
                    try {
                        lock.release();
                    } catch (Exception e) {
                    }
                }
            }).start();
        }
        countDownLatch.countDown();
    }
}
