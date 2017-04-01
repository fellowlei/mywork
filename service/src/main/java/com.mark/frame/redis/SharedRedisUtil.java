package com.mark.frame.redis;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lulei on 2017/4/1.
 */
public class SharedRedisUtil {
    private List<RedisUtil> pool = new ArrayList<RedisUtil>();

    public void init() {
        pool.add(new RedisUtil("192.168.81.134", 6379));
        pool.add(new RedisUtil("192.168.81.134", 6380));
    }

    public void init(List<String> ip_port_list){
        for(String ip_port : ip_port_list){
            String ip = ip_port.split(":")[0];
            Integer port = Integer.parseInt(ip_port.split(":")[1]);
            pool.add(new RedisUtil(ip,port));
        }
    }

    public RedisUtil getRedisUtil(String key) {
       Integer hash =  MurmurHash.hash32(key.getBytes(), key.length());
//        Long hash = MurmurHash.hash64(key.getBytes(), key.length());
        Integer index =  hash % pool.size();
        return pool.get(index);
    }

    public void getRedisUtilTest(String key) {
        Integer hash = MurmurHash.hash32(key.getBytes(), key.length());
//        Long hash =  MurmurHash.hash64(key.getBytes(), key.length());
        if(hash < 0)  hash = Math.abs(hash);
        Integer index = hash % pool.size();
        System.out.println(key + ":" + index);
        RedisUtil redisUtil = pool.get(index);
        System.out.println(redisUtil.dumpIp());
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
        redisUtil.getRedisUtilTest("name");
        System.out.println(redisUtil.get("name"));
    }

    public static void testHash(){
        SharedRedisUtil redisUtil = new SharedRedisUtil();
        redisUtil.init();
        for(int i=0; i<10; i++){
            redisUtil.getRedisUtilTest(i+"");
        }
    }


}
