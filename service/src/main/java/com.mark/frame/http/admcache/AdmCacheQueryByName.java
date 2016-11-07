package com.mark.frame.http.admcache;

import com.jayway.jsonpath.JsonPath;
import com.mark.frame.http.core.DefaultHttpClient;
import com.mark.frame.http.core.UserHttpMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lulei on 2016/11/7.
 */
public class AdmCacheQueryByName implements UserHttpMethod {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AdmCacheQueryByName(String name) {
        this.name = name;
    }

    @Override
    public Map<String, String> getHeaderMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Accept", "text/plain, */*; q=0.01");
        map.put("Accept-Encoding", "gzip, deflate");
        map.put("Accept-Language", "zh-CN,zh;q=0.8");
        map.put("Connection", "keep-alive");
        map.put("Cookie","");
        map.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        map.put("Host", "adm.cache.mark.com");
        map.put("Origin", "http://adm.cache.mark.com");
        map.put("Referer", "http://adm.cache.mark.com/clusters/1698?tab=2");
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36");
        map.put("X-Requested-With", "XMLHttpRequest");
        return map;
    }

    @Override
    public Map<String, String> getFormMap() {
        Map<String, String> map = new HashMap<String, String>();
        return map;
    }

    @Override
    public String getURL() {
        return "http://adm.cache.mark.com/clusters/list.json?clusterName="+name+"&dept1Name=&dept2Name=&ip=&port=&page=1&rows=10&sort=id&order=desc";
    }

    public static List<String> parseJson(String json){
        List<String> result = new ArrayList<String>();
        List<Object> list = JsonPath.read(json,"$.rows[*].id");
        for(Object id: list){
            System.out.println(id);
            result.add(id.toString());
        }
        return result;
    }
    public static List<String> queryByName(String name){
        String result = DefaultHttpClient.invokeGet(new AdmCacheQueryByName(name));
        return parseJson(result);
    }


    public static void main(String[] args) throws IOException {
//        HttpClientUtil.genMapCode();
        String name = "nginx_pin_mjq";
        String result = DefaultHttpClient.invokeGet(new AdmCacheQueryByName(name));
//        System.out.println(result);
        parseJson(result);
    }
}
