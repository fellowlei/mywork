package com.mark.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by lulei on 2016/2/24.
 */
public class Create_Sesssion_Sample_2 {
    public static void main(String[] args) throws InterruptedException {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.10.1:2181", 5000, 3000, retryPolicy);
        client.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
