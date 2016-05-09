package com.mark.nt.nty.mi;

import com.mark.frame.FastJsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lulei on 2016/3/25.
 */
public class MIPage {
    private static final ConcurrentHashMap<String, List> map = new ConcurrentHashMap<String, List>();

    public static void addUser(String name) {
        if (!map.containsKey(name)) {
            map.put(name, new ArrayList<String>());
        }
    }


    public static void sendTo(MiMessage miMessage) {
        addUser(miMessage.getTo());
        map.get(miMessage.getTo()).add(FastJsonUtils.toJson(miMessage));
    }

    public static void dump(){
        System.out.print(map.size() + ":");
        for(Map.Entry<String,List> enty: map.entrySet()){
            System.out.println(enty.getKey() + "#" + enty.getValue().size() + "#" + FastJsonUtils.toJson(enty.getValue()));
        }
    }
}
