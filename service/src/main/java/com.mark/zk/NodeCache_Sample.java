package com.mark.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Created by lulei on 2016/2/24.
 */
public class NodeCache_Sample {
    static String path = "/zk-book";

    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.10.1:2181").sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

    public static void main(String[] args) throws Exception {
        client.start();
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, "init".getBytes());

        final NodeCache cache = new NodeCache(client, path, false);
        cache.start();
        cache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("change : " + new String(cache.getCurrentData().getData()));
            }
        });

        client.setData().forPath(path, "u".getBytes());
        Thread.sleep(1000);
        client.delete().deletingChildrenIfNeeded().forPath(path);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
