package com.mark.frame.limit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lulei on 2017/12/7.
 */
public class SimpleLimit {

    private static Map<Long,AtomicInteger> limitMap = new ConcurrentHashMap<Long, AtomicInteger>();

    public static boolean limit(Integer limitSize){
        AtomicInteger count = limitMap.get(System.currentTimeMillis());
        if(count == null){
            limitMap.put(System.currentTimeMillis(),new AtomicInteger(1));
        }
        if(count.get() > limitSize){
            // limit
            return true;
        }
        return false;
    }
}
