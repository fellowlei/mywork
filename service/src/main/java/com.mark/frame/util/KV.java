package com.mark.frame.util;

import com.alibaba.dubbo.common.utils.CollectionUtils;

import java.util.*;

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
        Map<String,String> map = new HashMap<>();
        Map<String,Long> subMap = new HashMap<>();

        Map<String,KV> resultMap = new HashMap<>();

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
        Map<X,KV<Y,Z>> resultMap = new HashMap<>();

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

    /**
     * input
     * sku -> tcid
     *     -> veid
     * tcid -> t
     *
     * sku -> tcid -> t
     *     -> veid -> t
     * @return
     */
    public static Map<String,Map<String,Long>> genResult(){
        Map<String,Set<String>> skuMap = new HashMap<>();
        Map<String,Long> tcidMap = new HashMap<>();

        Map<String,Map<String,Long>> resuttMap = new HashMap<>();

        for(String key: skuMap.keySet()){
            Set<String> values = skuMap.get(key);
            if(CollectionUtils.isNotEmpty(values)){
                Map<String,Long> map = new HashMap<>();
                for(String tmp: values){
                    map.put(tmp,null);
                }
                resuttMap.put(key,map);
            }
        }

        Collection<Map<String, Long>> values = resuttMap.values();
        for(Map<String,Long> tmp: values){
            if(tmp != null){
                for(String key: tmp.keySet()){
                    Long tval = tcidMap.get(key);
                    if(tval != null){
                        tmp.put(key,tval);
                    }
                }
            }
        }

        return resuttMap;
    }

    /**
     * 分页查询，一次100
     */
    public static void queryByPage(){
        List<String> allList = new ArrayList<>();
        // 分页查询
        List<String> subList = new ArrayList<>();
        for(int i=1; i<=allList.size(); i++){
            subList.add(allList.get(i-1));
            if(i % 100 == 0){
//                doQuery(subList);
                subList =new ArrayList<String>();
            }
        }
//        doQuery(subList);
    }




}
