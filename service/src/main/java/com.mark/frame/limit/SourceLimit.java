package com.mark.frame.limit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lulei on 2017/12/7.
 */
public class SourceLimit {
    private static Map<String,ConcurrentHashMap<Long,AtomicInteger>> limitMap = new ConcurrentHashMap<String, ConcurrentHashMap<Long, AtomicInteger>>();

    public static boolean limitByTime(Map<Long,AtomicInteger> limit,Integer limitSize){
        AtomicInteger count = limit.get(System.currentTimeMillis());
        if(count == null){
            limit.put(System.currentTimeMillis(),new AtomicInteger(1));
        }
        if(count.get() > limitSize){
            // limit
            return true;
        }
        return false;
    }


    public static boolean limit(String source){
        ConcurrentHashMap<Long, AtomicInteger> limit = limitMap.get(source);
        if(limit == null){
           limit = new ConcurrentHashMap<Long, AtomicInteger>();
        }
        return limitByTime(limit,10);
    }

}
