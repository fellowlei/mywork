package com.mark.frame.qredis;

import redis.clients.jedis.Jedis;

/**
 * Created by lulei on 2016/3/2.
 */
public class RedisQ {
    public static void main(String[] args) {
       Jedis jedis =  new Jedis("192.168.10.1",6379);

    }
}
