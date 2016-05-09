package com.mark.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by lulei on 2016/2/24.
 */
public class Recipes_Barrier {
    static String lock_path = "/curator_barrier_path";
    static DistributedBarrier barrier;

    public static void main(String[] args) throws Exception {
        barrier2();

    }

    static void barrier1() throws Exception {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.10.1:2181").retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
                    client.start();
                    barrier = new DistributedBarrier(client, lock_path);
                    System.out.println(Thread.currentThread().getName() + " barrier");

                    try {
                        barrier.setBarrier();
                        barrier.waitOnBarrier();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("start...");
                }
            }).start();
        }
        Thread.sleep(2000);
        barrier.removeBarrier();
    }

    static void barrier2() {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.10.1:2181").retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
                        client.start();
                        DistributedDoubleBarrier barrier1 = new DistributedDoubleBarrier(client, lock_path, 5);
                        Thread.sleep(Math.round(Math.random() * 3000));

                        System.out.println(Thread.currentThread().getName() + " enter barrier");
                        barrier1.enter();
                        System.out.println("start ...");
                        Thread.sleep(Math.round(Math.random() * 3000));
                        barrier1.leave();
                        System.out.println("exit...");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
