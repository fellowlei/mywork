package com.mark.zk;

import com.github.zkclient.IZkChildListener;
import com.github.zkclient.ZkClient;

import java.util.List;

/**
 * Created by lulei on 2016/2/24.
 */
public class Create_Session_Sample {
    public static void main(String[] args) throws InterruptedException {
        String path = "/zk-book8";
        ZkClient zkClient = new ZkClient("192.168.10.1:2181", 5000);
        System.out.println("zk session connected");
//
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("s = [" + s + "], list = [" + list + "]");
            }
        });
        zkClient.createPersistent(path, true);
        Thread.sleep(1000);
        System.out.println(zkClient.getChildren(path));
        Thread.sleep(1000);
        zkClient.createPersistent(path + "/c1");
        Thread.sleep(1000);
        zkClient.deleteRecursive(path);
        Thread.sleep(Integer.MAX_VALUE);

    }
}
