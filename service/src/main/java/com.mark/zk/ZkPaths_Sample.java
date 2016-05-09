package com.mark.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.EnsurePath;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.ZooKeeper;

/**
 * Created by lulei on 2016/2/24.
 */
public class ZkPaths_Sample {
    static String path = "/curator_zkpath_sample";
    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.10.1:2181").retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

    public static void main(String[] args) throws Exception {
        client.start();
        ZooKeeper zk = client.getZookeeperClient().getZooKeeper();
        System.out.println(ZKPaths.fixForNamespace(path, "sub"));
        System.out.println(ZKPaths.makePath(path, "sub"));

        System.out.println(ZKPaths.getNodeFromPath("/curator_zkpath_sample/sub1"));

        ZKPaths.PathAndNode pn = ZKPaths.getPathAndNode(path + "/sub1");
        System.out.println(pn.getPath() + "," + pn.getNode());

        String dir1 = path + "/child1";
        String dir2 = path + "/child2";
        ZKPaths.mkdirs(zk, dir1);
        ZKPaths.mkdirs(zk, dir2);
        System.out.println(ZKPaths.getSortedChildren(zk, path));
        ZKPaths.deleteChildren(client.getZookeeperClient().getZooKeeper(), path, true);

//        client.usingNamespace("zk-book");
//
//        EnsurePath ensurePath = new EnsurePath(path);
//        ensurePath.ensure(client.getZookeeperClient());
//
//        EnsurePath ensurePath1 = client.newNamespaceAwareEnsurePath("/c1");
//        ensurePath1.ensure(client.getZookeeperClient());

    }

}
