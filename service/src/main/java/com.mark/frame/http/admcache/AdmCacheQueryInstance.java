package com.mark.frame.http.admcache;

import com.jayway.jsonpath.JsonPath;
import com.mark.frame.http.core.DefaultHttpClient;
import com.mark.frame.http.core.UserHttpMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lulei on 2016/11/7.
 */
public class AdmCacheQueryInstance implements UserHttpMethod {

    private String clusterId;

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getClusterId() {
        return clusterId;
    }

    public AdmCacheQueryInstance(String clusterId) {
        this.clusterId = clusterId;
    }

    @Override
    public Map<String, String> getHeaderMap() {
        Map<String,String> map = new HashMap<String, String>();
        map.put("Accept","text/plain, */*; q=0.01");
        map.put("Accept-Encoding","gzip, deflate");
        map.put("Accept-Language","zh-CN,zh;q=0.8");
        map.put("Connection","keep-alive");
        map.put("Cookie","");
        map.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        map.put("Host","adm.cache.mark.com");
        map.put("Origin","http://adm.cache.mark.com");
        map.put("Referer","http://adm.cache.mark.com/clusters/1698?tab=2");
        map.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36");
        map.put("X-Requested-With","XMLHttpRequest");
        return map;
    }

    @Override
    public Map<String, String> getFormMap() {
        return null;
    }

    @Override
    public String getURL() {
        return "http://adm.cache.mark.com/clusters/"+clusterId+"/instances.json";
    }

    public static Map<String,String> parseJson(String json){
        Map<String,String> resultMap = new HashMap<String,String>();
        List<Map<String,Object>> mapList =  JsonPath.read(json, "$.rows");
        for(Map<String,Object> map : mapList){
            System.out.println(map.get("id") + " " + map.get("clusterId") + " " + map.get("ipPort"));
            resultMap.put(map.get("id").toString(),map.get("ipPort").toString());
        }
        return resultMap;

//        Map<String,Object> map = JsonPath.read(json, "$.rows[*]");
//        for(Map.Entry<String,Object> entry: map.entrySet()){
//            System.out.println(entry.getKey() + "=" + entry.getValue());
//        }
    }

    public static Map<String,String> queryInstanceById(String id){
        String result = DefaultHttpClient.invokeGet(new AdmCacheQueryInstance(id));
        return parseJson(result);
    }

    public static void main(String[] args) throws IOException {
//        HttpClientUtil.genMapCode();

        String clusterId = "1698";
        String result = DefaultHttpClient.invokeGet(new AdmCacheQueryInstance(clusterId));
        parseJson(result);
//        System.out.println(result);
    }
}
