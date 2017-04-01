package com.mark.frame.redis;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lulei on 2017/4/1.
 */
public class SharedRedisUtil {
    List<RedisUtil> pool = new ArrayList<RedisUtil>();

    public void init() {
        pool.add(new RedisUtil("192.168.81.134", 6379));
        pool.add(new RedisUtil("192.168.81.134", 6380));
    }

    public RedisUtil getRedisUtil(String key) {
//       Integer hash =  MurmurHash.hash32(key.getBytes(), key.length());
        Long hash = MurmurHash.hash64(key.getBytes(), key.length());
        Integer index = pool.size() % hashCode();
        return pool.get(index);
    }

    public void getRedisUtilTest(String key) {
        Integer hash = MurmurHash.hash32(key.getBytes(), key.length());
//        Long hash =  MurmurHash.hash64(key.getBytes(), key.length());
        Integer index = hash % pool.size();
        System.out.println(index);
    }

    public String get(String key) {
        return getRedisUtil(key).get(key);
    }


    public String set(String key, String value) {
        return getRedisUtil(key).set(key, value);
    }

    public static void main(String[] args) {
        SharedRedisUtil redisUtil = new SharedRedisUtil();
        redisUtil.init();
        redisUtil.set("name","mark");
        System.out.println(redisUtil.get("name"));
    }


}
