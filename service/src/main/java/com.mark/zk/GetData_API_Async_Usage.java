package com.mark.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lulei on 2016/2/24.
 */
public class GetData_API_Async_Usage implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zk;

    public static void main(String[] args) throws Exception {
        String path = "/zk-book6";
        zk = new ZooKeeper("192.168.10.1:2181", 5000, new GetData_API_Async_Usage());

        countDownLatch.await();

        zk.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        zk.getData(path, true, new IDataCallback(), null);

        zk.setData(path, "123".getBytes(), -1);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                countDownLatch.countDown();
            }else if(event.getType()== Event.EventType.NodeDataChanged){
                zk.getData(event.getPath(),true,new IDataCallback(),null);
            }
        }
    }

    static class IDataCallback implements AsyncCallback.DataCallback{

        @Override
        public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
            System.out.println("rc = [" + rc + "], path = [" + path + "], ctx = [" + ctx + "], data = [" + data + "], stat = [" + stat + "]");
            System.out.println(stat.getCzxid() + "," + stat.getMzxid() + "," + stat.getVersion());
        }
    }
}
