package com.mark.frame.qredis;

import com.mark.service.RedisUtil;
import redis.clients.jedis.Jedis;

/**
 * Created by lulei on 2016/3/2.
 */
public class RProviderImpl implements RProvider<String> {

    private String key = "news";

    @Override
    public void lpush(String str) {
        Jedis jedis = RedisUtil.getJedis();
        jedis.lpush(key, str);
        RedisUtil.returnResource(jedis);
    }

    @Override
    public String rpop() {
        Jedis jedis = RedisUtil.getJedis();
        String result = jedis.rpop(key);
        RedisUtil.returnResource(jedis);
        return result;
    }

    @Override
    public Long llen() {
        Jedis jedis = RedisUtil.getJedis();
        Long result = jedis.llen(key);
        RedisUtil.returnResource(jedis);
        return result;
    }
}
