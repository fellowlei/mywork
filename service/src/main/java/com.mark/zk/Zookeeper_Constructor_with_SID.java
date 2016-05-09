package com.mark.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by lulei on 2016/2/23.
 */
public class Zookeeper_Constructor_with_SID implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("192.168.10.1:2181", 500, new Zookeeper_Constructor_with_SID());
        long sessionId = zooKeeper.getSessionId();
        byte[] passwd = zooKeeper.getSessionPasswd();

        zooKeeper = new ZooKeeper("192.168.10.1:2181", 500, new Zookeeper_Constructor_with_SID(), 1L, "test".getBytes());

        zooKeeper = new ZooKeeper("192.168.10.1:2181", 500, new Zookeeper_Constructor_with_SID(), sessionId, passwd);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("receive watched event: " + watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            countDownLatch.countDown();
        }
    }
}
