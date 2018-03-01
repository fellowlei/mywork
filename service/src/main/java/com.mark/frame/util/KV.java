package com.mark.frame.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lulei on 2018/3/1.
 * 合并map 工具类，映射2个map关系
 * 输入 map<a,b>,map<b,c>  结果是map<a,b,c>
 * map<a,b> map<b,c>  reult map<a,b,c>
 */
public class KV<K,V> {
    private K k;
    private V v;

    public KV(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public K getK() {
        return k;
    }

    public void setK(K k) {
        this.k = k;
    }

    public V getV() {
        return v;
    }

    public void setV(V v) {
        this.v = v;
    }

    /**
     * 合并map
     * map<a,b>
     * map<b,c>
     * reult map<a,b,c>
     *
     * @return
     */
    public static  Map<String,KV> mergeMap(){
        Map<String,String> map = new HashMap<String,String>();
        Map<String,Long> subMap = new HashMap<String,Long>();

        Map<String,KV> resultMap = new HashMap<String,KV>();

        for(String key:map.keySet()){
            resultMap.put(key,new KV(map.get(key),null));
        }

        Collection<KV> kvs = resultMap.values();

        for(KV kv:kvs){
            if(subMap.get(kv.getK()) != null){
                kv.setV(subMap.get(kv.getK()));
            }
        }

        return resultMap;
    }

    /**
     * 合并map
     * map<a,b>
     * map<b,c>
     * reult map<a,b,c>
     *
     * @return
     */
    public static <X,Y,Z>  Map<X,KV<Y,Z>> mergeMap(Map<X,Y> map,Map<Y,Z> subMap){
        Map<X,KV<Y,Z>> resultMap = new HashMap<X,KV<Y,Z>>();

        for(X key:map.keySet()){
            resultMap.put(key,new KV(map.get(key),null));
        }

        Collection<KV<Y,Z>> kvs = resultMap.values();

        for(KV<Y,Z> kv:kvs){
            if(subMap.get(kv.getK()) != null){
                kv.setV(subMap.get(kv.getK()));
            }
        }

        return resultMap;
    }


}
