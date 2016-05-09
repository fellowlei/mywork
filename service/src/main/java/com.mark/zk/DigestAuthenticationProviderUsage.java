package com.mark.zk;

import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.NoSuchAlgorithmException;

/**
 * Created by lulei on 2016/2/25.
 */
public class DigestAuthenticationProviderUsage {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(DigestAuthenticationProvider.generateDigest("foo:zk-book"));
    }
}
