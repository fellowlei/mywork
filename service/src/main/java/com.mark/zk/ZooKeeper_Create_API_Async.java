package com.mark.zk;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lulei on 2016/2/23.
 */
public class ZooKeeper_Create_API_Async implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("192.168.10.1:2181", 5000, new ZooKeeper_Create_API_Async());
        countDownLatch.await();

        zooKeeper.create("/zk-test-e", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new IStringCallback(), "i am context.");

        zooKeeper.create("/zk-test-e", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new IStringCallback(), "i am context");

        zooKeeper.create("/zk-test-e", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, new IStringCallback(), "i am context");
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            countDownLatch.countDown();
        }
    }

    static class IStringCallback implements AsyncCallback.StringCallback {

        @Override
        public void processResult(int i, String s, Object o, String s1) {
            System.out.println("i = [" + i + "], s = [" + s + "], o = [" + o + "], s1 = [" + s1 + "]");
        }
    }
}


