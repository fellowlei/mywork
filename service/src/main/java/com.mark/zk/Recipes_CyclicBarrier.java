package com.mark.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lulei on 2016/2/24.
 */
public class Recipes_CyclicBarrier {
    static String lock_path = "/curator_atomic_path";
    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.10.1:2181").retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

    public static CyclicBarrier barrier = new CyclicBarrier(3);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(new Runner("one"));
        executorService.submit(new Runner("two"));
        executorService.submit(new Runner("three"));
        executorService.shutdown();
    }

    static class Runner implements Runnable {
        private String name;

        public Runner(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + " ready.");
            try {
                barrier.await();
            } catch (Exception e) {

            }
            System.out.println(name + " run!");
        }
    }
}
