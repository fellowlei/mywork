package com.mark.zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by lulei on 2016/2/23.
 */
public class ZooKeeper_Delete_Sync_Usage implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        String path = "/zk-book2";
        ZooKeeper zk = new ZooKeeper("192.168.10.1:2181",5000,new ZooKeeper_Delete_Sync_Usage());

        zk.create(path,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        zk.delete(path,-1);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                countDownLatch.countDown();
            }
        }
    }
}
