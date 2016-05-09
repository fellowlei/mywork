package com.mark.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Created by lulei on 2016/2/24.
 */
public class PathChildrenCache_Sample {
    static String path = "/zk-book9";

    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.10.1:2181").sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

    public static void main(String[] args) throws Exception {
        client.start();
        PathChildrenCache cache = new PathChildrenCache(client, path, true);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);

        cache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                switch (pathChildrenCacheEvent.getType()) {
                    case CHILD_ADDED:
                        System.out.println("child_added: " + pathChildrenCacheEvent.getData().getPath());
                        break;
                    case CHILD_UPDATED:
                        System.out.println("updated: " + pathChildrenCacheEvent.getData().getPath());
                        break;
                    case CHILD_REMOVED:
                        System.out.println("removed: " + pathChildrenCacheEvent.getData().getPath());
                        break;
                    default:
                        break;
                }
            }
        });

        client.create().withMode(CreateMode.PERSISTENT).forPath(path);

        client.create().withMode(CreateMode.PERSISTENT).forPath(path + "/c1");
        Thread.sleep(1000);

        client.delete().forPath(path + "/c1");
        Thread.sleep(1000);
        client.delete().forPath(path);
        Thread.sleep(1000);
    }
}
