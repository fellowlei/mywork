package com.mark.frame.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.*;

/**
 * Created by lulei on 2018/3/26.
 */
public class CacheUtil {

    static  class  User {
        String id;
        String name;
        String age;

        public User(String id, String name, String age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", age='" + age + '\'' +
                    '}';
        }
    }
    public  static Cache<String,User> USER_CACHE= CacheBuilder.newBuilder()
            .initialCapacity(10)//
            .maximumSize(10)//
            .expireAfterWrite(2, TimeUnit.SECONDS)// 根据某个键被创建或值被替换后多少时间移除
            .concurrencyLevel(16) // 允许同时最多10个线程并发修改，默认是4,配置建议是2的倍数
            .recordStats()//统计信息
            .build();

    public static User getFromCache(final String key) throws ExecutionException {
        User user = USER_CACHE.get(key, new Callable<User>() {
            @Override
            public User call() throws Exception {

                User user2 = new User(key, "mark02", "100");
                System.out.println("CacheUtil.call init cache " + user2);
                return user2;
            }
        });
        return user;
    }

    public static User getFromCacheForUser(String key){
        try {
            return getFromCache(key);
        } catch (ExecutionException e) {
            User user3 = new User("03", "mark03", "19");
            return user3;
        }
    }

    public static void main(String[] args) {


        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        final User user =new User("01","mark01","18");
//        User user2 =new User("02","mark02","19");

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10; i++){
                    User user1 = getFromCacheForUser("01");
                    System.out.println(user1);
                    User user2 = getFromCacheForUser("02");
                    System.out.println(user2);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.out.println("end");

    }
}
