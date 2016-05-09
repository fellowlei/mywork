package com.mark.zk;


import com.github.zkclient.IZkDataListener;
import com.github.zkclient.ZkClient;

/**
 * Created by lulei on 2016/2/24.
 */
public class Get_Data_Sample {
    public static void main(String[] args) throws InterruptedException {
        String path = "/zk-book9";
        ZkClient zkClient = new ZkClient("192.168.10.1:2181", 5000);
        zkClient.createEphemeral(path, "123".getBytes());

        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, byte[] bytes) throws Exception {
                System.out.println("change s = [" + s + "], bytes = [" + bytes + "]");
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("delete s = [" + s + "]");
            }
        });

        System.out.println(zkClient.readData(path));
        zkClient.writeData(path, "456".getBytes());
        Thread.sleep(1000);
        zkClient.delete(path);
        Thread.sleep(1000);
    }
}
