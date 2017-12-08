package com.mark.frame.limit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lulei on 2017/12/7.
 */
public class SpendSimpleLimit {

    private static Map<Long,AtomicInteger> limitMap = new ConcurrentHashMap<Long, AtomicInteger>();

    public static boolean limit(Integer limitSize){
        AtomicInteger count = limitMap.get(System.currentTimeMillis());
        if(count == null){
            limitMap.put(System.currentTimeMillis(),new AtomicInteger(1));
        }
        if(getLimitCount() > limitSize){
            // limit
            return true;
        }
        return false;
    }

    public static Integer getLimitCount(){
        long currentTimeMillis = System.currentTimeMillis();
        Integer sum =0;
        for(int i=0; i<5; i++){
            AtomicInteger count = limitMap.get(currentTimeMillis - i);
            if(count != null){
                sum = sum + count.get();
            }
        }
        return sum;
    }

    public void clear(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                long currentTimeMillis = System.currentTimeMillis();
                Map<Long,AtomicInteger> newLimitMap = new ConcurrentHashMap<Long, AtomicInteger>();
                for(int i=0; i<5; i++){
                    long time =  currentTimeMillis-i;
                    AtomicInteger count = limitMap.get(time);
                    if(count == null){
                        break;
                    }
                    newLimitMap.put(time,count);
                }
                limitMap = newLimitMap;
            }
        }).start();
    }
}
