package com.mark.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Created by lulei on 2016/2/24.
 */
public class Create_Session_Sample_fluent {
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.10.1:2181").sessionTimeoutMs(5000).retryPolicy(retryPolicy).build();
        client.start();
        System.out.println("start over");
        Thread.sleep(Integer.MAX_VALUE);

        CuratorFrameworkFactory.builder().connectString("192.168.10.1:2181").sessionTimeoutMs(5000).retryPolicy(retryPolicy).namespace("base").build();

        String path = "/zoo_demo";
        client.create().forPath(path);

        client.create().forPath(path,"init".getBytes());

        client.create().withMode(CreateMode.EPHEMERAL).forPath(path);

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);




    }
}
