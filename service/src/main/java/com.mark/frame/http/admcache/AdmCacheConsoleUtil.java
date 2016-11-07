package com.mark.frame.http.admcache;

import com.mark.frame.http.core.DefaultHttpClient;
import com.mark.frame.http.core.UserHttpMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lulei on 2016/11/7.
 */
public class AdmCacheConsoleUtil implements UserHttpMethod {

    private String clusterId;
    private String instanceId;

    public AdmCacheConsoleUtil(String clusterId, String instanceId) {
        this.clusterId = clusterId;
        this.instanceId = instanceId;
    }

    @Override
    public Map<String, String> getHeaderMap() {
        Map<String,String> map = new HashMap<String, String>();
        map.put("Accept","text/plain, */*; q=0.01");
        map.put("Accept-Encoding","gzip, deflate");
        map.put("Accept-Language","zh-CN,zh;q=0.8");
        map.put("Cookie","");
        map.put("Connection","keep-alive");
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
        Map<String,String> map = new HashMap<String, String>();
        map.put("cmd","info");
        return map;
    }

    @Override
    public String getURL() {
        return "http://adm.cache.mark.com/clusters/"+clusterId+"/instances/"+instanceId+"/console/";
    }

    public static String executeAdmCache(String clusterId,String instanceId){
        String result = DefaultHttpClient.invokePost(new AdmCacheConsoleUtil(clusterId,instanceId));
        return result;
    }

    public static void main(String[] args) throws IOException {
//        HttpClientUtil.genMapCode();
        String clusterId = "1698";
        String instanceId = "123952";
        String result = DefaultHttpClient.invokePost(new AdmCacheConsoleUtil(clusterId,instanceId));

//        System.out.println(result);
    }
}
