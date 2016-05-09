package com.mark.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lulei on 2016/2/24.
 */
public class Create_Node_Background_Sample {
    static String path = "/zk-book";

    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.10.1:2181").sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

    static CountDownLatch countDownLatch = new CountDownLatch(2);
    static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws Exception {
        client.start();
        System.out.println(Thread.currentThread().getName());

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                System.out.println("code : " + curatorEvent.getResultCode() + ",type: " + curatorEvent.getType());
                System.out.println("processResult : " + Thread.currentThread().getName());
                countDownLatch.countDown();
            }
        }, executorService).forPath(path, "init".getBytes());

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                System.out.println("code 2: " + curatorEvent.getResultCode() + ",type: " + curatorEvent.getType());
                System.out.println("result2: " + Thread.currentThread().getName());
                countDownLatch.countDown();
            }
        }).forPath(path, "init".getBytes());

        countDownLatch.await();
        executorService.shutdown();

    }
}
