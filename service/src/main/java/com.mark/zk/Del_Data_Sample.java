package com.mark.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * Created by lulei on 2016/2/24.
 */
public class Del_Data_Sample {
    static String path = "/zk-book/c1";

    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.10.1:2181").sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

    public static void main(String[] args) throws Exception {
//        client.start();
//        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, "init".getBytes());
//
//        Stat stat = new Stat();
//        client.getData().storingStatIn(stat).forPath(path);
//        client.delete().deletingChildrenIfNeeded().withVersion(stat.getVersion()).forPath(path);
//
//
//        client.getData().forPath(path);
//
//        client.getData().storingStatIn(stat).forPath(path);


//        client.start();
//
//        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, "init".getBytes());
//
//        Stat  stat = new Stat();
//        System.out.println(new String(client.getData().storingStatIn(stat).forPath(path)));
//
//
//        client.setData().forPath(path);
//
//        client.setData().withVersion(1).forPath(path);

        client.start();

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path,"init".getBytes());

        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath(path);

        System.out.println(path);
        System.out.println(client.setData().withVersion(stat.getVersion()).forPath(path).getVersion());

        client.setData().withVersion(stat.getVersion()).forPath(path);


    }
}
