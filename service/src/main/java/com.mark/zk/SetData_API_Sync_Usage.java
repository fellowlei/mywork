package com.mark.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lulei on 2016/2/24.
 */
public class SetData_API_Sync_Usage implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zk;

    public static void main(String[] args) throws Exception {
        String path = "/zk-book7";
        zk = new ZooKeeper("192.168.10.1:2181", 5000, new SetData_API_Sync_Usage());

        countDownLatch.await();

        zk.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zk.getData(path, true, null);

        Stat stat = zk.setData(path, "456".getBytes(), -1);

        System.out.println(stat.getCzxid() + "," + stat.getMzxid() + "," + stat.getVersion());

        Stat stat2 = zk.setData(path, "456".getBytes(), stat.getVersion());
        System.out.println(stat2.getCzxid() + "," + stat2.getMzxid() + "," + stat2.getVersion());

        try {
            zk.setData(path, "456".getBytes(), stat.getVersion());
        } catch (KeeperException e) {
            System.out.println("error: " + e.getCode() + "," + e.getMessage());
        }
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                countDownLatch.countDown();
            }
        }
    }
}
