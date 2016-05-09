package com.mark.zk;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lulei on 2016/2/23.
 */
public class ZooKeeper_Create_API_Sync_Useage implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {

        ZooKeeper zooKeeper = new ZooKeeper("192.168.10.1:2181", 5000, new ZooKeeper_Create_API_Sync_Useage());

        String path1 = zooKeeper.create("/zk-test-e", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("success create node " + path1);

        String path2 = zooKeeper.create("/zk-test-e", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("success crete node : " + path2);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            countDownLatch.countDown();
        }
    }
}
