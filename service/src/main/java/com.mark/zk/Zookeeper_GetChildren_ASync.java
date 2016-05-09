package com.mark.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by lulei on 2016/2/23.
 */
public class Zookeeper_GetChildren_ASync implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ZooKeeper zk = null;

    public static void main(String[] args) throws Exception {
        String path = "/zk-book4";
        zk = new ZooKeeper("192.168.10.1:2181", 5000, new Zookeeper_GetChildren_ASync());
        countDownLatch.await();

        zk.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zk.create(path + "/c1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        zk.getChildren(path, true, new IChildrenCallback(), null);

        zk.create(path + "/c2", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()){
            if(Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()){
                countDownLatch.countDown();
            }else if(watchedEvent.getType() == Event.EventType.NodeChildrenChanged){
                try {
                    System.out.println("get child: " + zk.getChildren(watchedEvent.getPath(),true));
                } catch (Exception e) {
                }
            }
        }

    }

    static class IChildrenCallback implements AsyncCallback.Children2Callback {

        @Override
        public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
            System.out.println("rc = [" + rc + "], path = [" + path + "], ctx = [" + ctx + "], children = [" + children + "], stat = [" + stat + "]");
        }
    }
}
