package com.mark.service;

import redis.clients.jedis.*;
import redis.clients.util.ShardInfo;

import java.util.*;

/**
 * Created by lulei on 2016/3/2.
 */
public class TestRedis {

    public void testString() {
        Jedis jedis = new Jedis("192.168.10.1", 6379);
        jedis.set("name", "xinxin");
        System.out.println(jedis.get("name"));

        jedis.append("name", " is my lover");
        System.out.println(jedis.get("name"));

        jedis.mset("name", "liuling", "age", "23", "qq", "4123424");
        jedis.incr("age");
        System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
    }

    public void testMap() {
        Jedis jedis = new Jedis("192.168.10.1", 6379);

        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "xinxin");
        map.put("age", "22");
        map.put("qq", "123456");
        jedis.hmset("user", map);

        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println(rsmap);

        jedis.hdel("user", "age");
        System.out.println(jedis.hmget("user", "age"));
        System.out.println(jedis.hlen("user"));
        System.out.println(jedis.exists("user"));
        System.out.println(jedis.hkeys("user"));
        System.out.println(jedis.hvals("user"));

        Iterator<String> it = jedis.hkeys("user").iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key + ":" + jedis.hmget("user", key));
        }
    }

    public void testList() {
        Jedis jedis = new Jedis("192.168.10.1", 6379);

        jedis.del("java");
        System.out.println(jedis.lrange("java", 0, -1));
        jedis.lpush("java", "spring");
        jedis.lpush("java", "struts");
        jedis.lpush("java", "hibernate");
        System.out.println(jedis.lrange("java", 0, -1));

        jedis.del("java");
        jedis.rpush("java", "spring");
        jedis.rpush("java", "struts");
        jedis.rpush("java", "hibernate");
        System.out.println(jedis.lrange("java", 0, -1));
    }

    public void testSet() {
        Jedis jedis = new Jedis("192.168.10.1", 6379);

        jedis.del("user");
        jedis.sadd("user", "liuling");
        jedis.sadd("user", "xinxin");
        jedis.sadd("user", "ling");
        jedis.sadd("user", "zhangxinxin");
        jedis.sadd("user", "who");

        jedis.srem("user", "who");

        System.out.println(jedis.smembers("user"));
        System.out.println(jedis.sismember("user", "who"));
        System.out.println(jedis.srandmember("user"));
        System.out.println(jedis.scard("user"));
    }

    public void test() {
        Jedis jedis = new Jedis("192.168.10.1", 6379);

        jedis.del("a");
        jedis.rpush("a", "1");
        jedis.lpush("a", "6");
        jedis.lpush("a", "3");
        jedis.lpush("a", "9");
        System.out.println(jedis.lrange("a", 0, -1));
        System.out.println(jedis.sort("a"));
        System.out.println(jedis.lrange("a", 0, -1));
    }

    public void testRedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1024);
        jedisPoolConfig.setMaxIdle(200);
        jedisPoolConfig.setMaxWaitMillis(1000);
        jedisPoolConfig.setTestOnBorrow(true);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.10.1", 6379, 10000);
        System.out.println(jedisPool.getNumActive());
        System.out.println(jedisPool.getNumIdle());
        System.out.println(jedisPool.getNumWaiters());
        Jedis jedis = jedisPool.getResource();
        jedis.set("name", "中文测试");
        Jedis jedis2 = jedisPool.getResource();
        System.out.println(jedis2.get("name"));
    }

    public void testRedisPool2() {
        RedisUtil.getJedis().set("name", "中文测试");
        System.out.println(RedisUtil.getJedis().get("name"));
    }

    private static JedisPool pool = null;

    public static JedisPool getPool() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(500);
            config.setMaxIdle(5);
            config.setMaxWaitMillis(100 * 10);
            config.setTestOnBorrow(true);
            pool = new JedisPool(config, "192.168.10.1", 6379);
        }
        return pool;
    }

    public static void returnResouce(JedisPool pool, Jedis jedis) {
        if (jedis != null) {
            pool.returnResource(jedis);
        }
    }

    public static String get(String key) {
        String value = null;
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResouce(pool, jedis);
        }
        return value;
    }


    public void test2() {
        Jedis jedis = new Jedis("192.168.10.1", 11211);


//        Transaction t = jedis.multi();
//        t.set("foo","bar");
//        t.exec();
//        System.out.println(jedis.get("foo"));

//        Transaction t =  jedis.multi();
//        t.set("foo1","bar");
//        Response<String> result1 = t.get("foo1");
//        t.zadd("foo",1,"bar");
//        t.zadd("foo",0,"bari");
//        t.zadd("foo",0,"barik");
//        Response<Set<String>> sose = t.zrange("foo", 0, -1);
//        t.exec();
//
//        System.out.println(result1.get());
//        System.out.println(sose);

//        Pipeline p = jedis.pipelined();
//        p.set("foo1", "bar");
//        p.zadd("foo", 1, "baro");
//        p.zadd("foo", 0, "bari");
//        p.zadd("foo", 0, "barik");
//        Response<String> pipe = p.get("foo1");
//        Response<Set<String>> sose = p.zrange("foo", 0, -1);
//
//        p.sync();
//        System.out.println(pipe.get());
//        System.out.println(sose);

//        MyListener listener = new MyListener();
//        jedis.subscribe(listener,"foo");

        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        JedisShardInfo si = new JedisShardInfo("192.168.10.1", 6379);
        shards.add(si);
//        si = new JedisShardInfo("192.168.10.1", 6380);
//        si.setPassword("pass");
//        shards.add(si);

        ShardedJedis shardedJedis = new ShardedJedis(shards,ShardedJedis.DEFAULT_KEY_TAG_PATTERN);
        shardedJedis.set("a", "foo");
        System.out.println(shardedJedis.get("a"));
        ShardInfo sis = shardedJedis.getShardInfo("a");
        System.out.println(sis);
        shardedJedis.disconnect();

        ShardedJedisPool pool = new ShardedJedisPool(new JedisPoolConfig(), shards);
        ShardedJedis shardedJedis1 = pool.getResource();
        shardedJedis1.set("a", "foo");
        System.out.println(shardedJedis1.get("a"));
        pool.returnResource(shardedJedis1);


        ShardedJedis shardedJedis2 = pool.getResource();
        shardedJedis2.set("z", "bar");
        System.out.println(shardedJedis2.get("z"));
        pool.returnResource(shardedJedis2);
        pool.destroy();

    }

    public static void main(String[] args) {
//        new TestRedis().test2();
        Jedis jedis = new Jedis("192.168.10.1", 22121);
//        for(int i=0; i<100; i++){
//            jedis.set("test",i+"",i+"");
//        }
        jedis.set("A","A");


    }

    static class MyListener extends JedisPubSub {
        @Override
        public void onMessage(String channel, String message) {
            System.out.println(channel + ":" + message);
        }
    }
}
