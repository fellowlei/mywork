package com.mark.frame.qredis;

import com.mark.service.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lulei on 2016/3/2.
 */
public class RedisQueueTest {

    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);
    private ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(100);
    private static AtomicInteger atomicInteger = new AtomicInteger();


    public void consumer() {
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                System.out.println("RedisQueueTest.run");
                scheduledThreadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            consume();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 1, 1000, TimeUnit.MILLISECONDS);
    }

    public void producer() {
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                scheduledThreadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            produce();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 1, 1000, TimeUnit.MILLISECONDS);
    }

    private void consume() throws InterruptedException {
        Jedis jedis = RedisUtil.getJedis();
        Long num = jedis.llen("news");
        int size = num > 5 ? 5 : num.intValue();
        if (num > 0) {
            for (int i = 0; i < size; i++) {
                System.out.println(Thread.currentThread() + "-" + jedis.rpop("news"));
            }
        } else {
            System.out.println("news empty");
        }
        RedisUtil.returnResource(jedis);
        Thread.sleep(5000);
    }

    private void produce() throws InterruptedException {
        Jedis jedis = RedisUtil.getJedis();
        for (int i = 0; i < 5; i++) {
            jedis.lpush("news", atomicInteger.incrementAndGet() + "");

        }
        RedisUtil.returnResource(jedis);
        Thread.sleep(5000);
    }

    public void start() {
        producer();
        consumer();
    }


    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new RedisQueueTest().start();
                }
            }).start();
        }
        new RedisQueueTest().start();
        System.out.println("over");
    }
}
