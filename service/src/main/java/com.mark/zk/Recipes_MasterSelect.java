package com.mark.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by lulei on 2016/2/24.
 */
public class Recipes_MasterSelect {
    static String path = "/curator_recipes_master_path";
    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.10.1:2181").retryPolicy(new ExponentialBackoffRetry(1000,3)).build();

    public static void main(String[] args) throws InterruptedException {
        client.start();

        LeaderSelector selector= new LeaderSelector(client, path, new LeaderSelectorListener() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                System.out.println("master: ");
                Thread.sleep(2000);
                System.out.println("release master");
            }

            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {

            }
        });

        selector.autoRequeue();
        selector.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
