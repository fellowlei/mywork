package com.mark.zk;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lulei on 2016/2/24.
 */
public class Exist_API_Sync_Useage implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zk;

    public static void main(String[] args) throws Exception {
        String path = "/zk-book8";
        zk = new ZooKeeper("192.168.10.1:2181", 5000, new Exist_API_Sync_Useage());

        countDownLatch.await();

        zk.exists(path, true);

        zk.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zk.setData(path, "123".getBytes(), -1);

        zk.create(path + "/c1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zk.delete(path + "/c1", -1);
        zk.delete(path, -1);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent event) {
        try {
            if (Event.KeeperState.SyncConnected == event.getState()) {
                if (Event.EventType.None == event.getType() && null == event.getPath()) {
                    countDownLatch.countDown();
                } else if (Event.EventType.NodeCreated == event.getType()) {
                    System.out.println("node " + event.getPath() + " created");
                    zk.exists(event.getPath(), true);
                } else if (Event.EventType.NodeDeleted == event.getType()) {
                    System.out.println("node " + event.getPath() + " deleted");
                    zk.exists(event.getPath(), true);
                } else if (Event.EventType.NodeDataChanged == event.getType()) {
                    System.out.println("node " + event.getPath() + " data change");
                    zk.exists(event.getPath(), true);
                }
            }
        } catch (Exception e) {
        }
    }
}
