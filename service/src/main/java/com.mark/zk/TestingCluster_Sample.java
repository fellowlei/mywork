package com.mark.zk;

import org.apache.curator.test.TestingCluster;
import org.apache.curator.test.TestingZooKeeperServer;

/**
 * Created by lulei on 2016/2/24.
 */
public class TestingCluster_Sample {
    public static void main(String[] args) throws Exception {
        TestingCluster cluster = new TestingCluster(3);
        cluster.start();
        Thread.sleep(2000);
        TestingZooKeeperServer leader = null;
        for (TestingZooKeeperServer server : cluster.getServers()) {
            System.out.print(server.getInstanceSpec().getServerId() + "-");
            System.out.print(server.getQuorumPeer().getServerState() + "-");
            System.out.print(server.getInstanceSpec().getDataDirectory().getAbsoluteFile());
            System.out.println();
            if (server.getQuorumPeer().getServerState().equals("leading")) {
                leader = server;
            }
        }
        leader.kill();
        System.out.println("after---------");
        for (TestingZooKeeperServer server : cluster.getServers()) {
            System.out.print(server.getInstanceSpec().getServerId() + "-");
            System.out.print(server.getQuorumPeer().getServerState());
            System.out.print(server.getInstanceSpec().getDataDirectory().getAbsoluteFile());
            System.out.println();
        }
        cluster.stop();

    }
}
