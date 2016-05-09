package com.mark.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * Created by lulei on 2016/2/24.
 */
public class AuthSample {
    static String path = "/zk-book_auth_test";

    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper("192.168.10.1:2181", 5000, null);

        zk.addAuthInfo("digest", "foo:true".getBytes());
        zk.create(path, "init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);

        ZooKeeper zk2 = new ZooKeeper("192.168.10.1:2181", 5000, null);
        zk2.addAuthInfo("digest", "foo:true".getBytes());
        System.out.println(zk2.getData(path, false, null));

        ZooKeeper zk3 = new ZooKeeper("192.168.10.1:2181", 5000, null);
        zk3.addAuthInfo("digest", "foo:false".getBytes());
        zk3.getData(path, false, null);
        Thread.sleep(Integer.MAX_VALUE);

    }

    static void auth() throws Exception {
        ZooKeeper zk = new ZooKeeper("192.168.10.1:2181", 5000, null);

        zk.addAuthInfo("digest", "foo:true".getBytes());
        zk.create(path, "init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);

        Thread.sleep(Integer.MAX_VALUE);
    }
}
