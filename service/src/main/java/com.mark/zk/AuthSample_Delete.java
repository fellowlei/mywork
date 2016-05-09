package com.mark.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by lulei on 2016/2/24.
 */
public class AuthSample_Delete {
    static String path = "/zk-book_auth_test2";
    static String path2 = "/zk-book_auth_test2/child";

    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper("192.168.10.1:2181",5000,null);
        zk.addAuthInfo("digest","foo:true".getBytes());

        zk.create(path,"init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);

        zk.create(path2,"init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL,CreateMode.EPHEMERAL);

        ZooKeeper zk2 =new ZooKeeper("192.168.10.1:2181",5000,null);
        try {
            zk2.delete(path2, -1);
        }catch (Exception e){
            System.out.println("delete failed: " + e.getMessage());
        }

        ZooKeeper zk3= new ZooKeeper("192.168.10.1:2181",5000,null);
        zk3.addAuthInfo("digest","foo:true".getBytes());
        zk3.delete(path2,-1);
        System.out.println("delete success " + path2);

        ZooKeeper zk4 = new ZooKeeper("192.168.10.1:2181",5000,null);
        zk4.delete(path,-1);
        System.out.println("delete success " + path);




    }
}
