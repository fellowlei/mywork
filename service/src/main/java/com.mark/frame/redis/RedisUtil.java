package com.mark.frame.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by lulei on 2017/4/1.
 */
public class RedisUtil {
    private JedisPool pool = null;
    private Integer timeout = 1000 * 50;

    public RedisUtil(String ip, int port) {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(500);
            config.setMaxIdle(5);
            config.setMaxWaitMillis(timeout);
            config.setTestOnBorrow(true);
            pool = new JedisPool(config, ip, port, timeout);
        }
    }

    public RedisUtil(JedisPoolConfig config, String ip, int port) {
        if (pool == null) {
            pool = new JedisPool(config, ip, port, timeout);
        }
    }


    public String get(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            pool.returnResource(jedis);
            jedis.close();
        }
        return value;
    }


    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.set(key, value);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return "0";
        } finally {
            pool.returnResource(jedis);
        }
    }

}
