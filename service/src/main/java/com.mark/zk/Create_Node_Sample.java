package com.mark.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Created by lulei on 2016/2/24.
 */
public class Create_Node_Sample {

    static String path = "/zk-book/c2";
    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.10.1:2181").sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

    public static void main(String[] args) throws Exception {
        client.start();
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path,"init".getBytes());

        client.delete().forPath(path);

        client.delete().deletingChildrenIfNeeded().forPath(path);

        client.delete().guaranteed().forPath(path);


    }
}
