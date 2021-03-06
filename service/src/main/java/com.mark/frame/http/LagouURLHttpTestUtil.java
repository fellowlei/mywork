package com.mark.frame.http;

import com.mark.frame.FastJsonUtils;
import com.mark.frame.http.core.URLAbstractHttpClient;
import com.mark.frame.http.core.UserHttpMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lulei on 2016/5/6.
 */
public class LagouURLHttpTestUtil implements UserHttpMethod {
    @Override
    public Map<String, String> getHeaderMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Accept", "application/json, text/javascript, */*; q=0.01");
        map.put("Accept-Encoding", "gzip, deflate");
        map.put("Accept-Language", "zh-CN,zh;q=0.8");
        map.put("Connection", "keep-alive");
        map.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        map.put("Host", "www.lagou.com");
        map.put("Origin", "http://www.lagou.com");
        map.put("Referer", "http://www.lagou.com/jobs/list_Java?city=%E5%8C%97%E4%BA%AC&cl=false&fromSearch=true&labelWords=&suginput=");
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
        map.put("X-Requested-With", "XMLHttpRequest");
        return map;
    }

    @Override
    public Map<String, String> getFormMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("first", "false");
        map.put("pn", "1");
        map.put("kd", "Java");
        return map;
    }

    @Override
    public String getURL() {
        return "http://www.lagou.com/jobs/positionAjax.json?city=%E5%8C%97%E4%BA%AC";
    }

    public static void dumpLagouResult(String result) {
        System.out.println("--------------------------------------------------");
        Map<String, Object> map = FastJsonUtils.fromJson(result, Map.class);
        Map<String, Object> map2 = (Map<String, Object>) map.get("content");
        List<Map> list = (List) map2.get("result");
        for (Map map3 : list) {
            System.out.println(FastJsonUtils.toJson(map3));
        }
    }
    public static void main(String[] args) {
        LagouURLHttpTestUtil httpLagouTest =  new LagouURLHttpTestUtil();
        URLAbstractHttpClient client = new URLAbstractHttpClient();
//        String result = client.doPost(httpLagouTest.getURL(),httpLagouTest.getHeaderMap(),httpLagouTest.getFormMap());
        String result = client.doGet(httpLagouTest.getURL(), httpLagouTest.getHeaderMap(), httpLagouTest.getFormMap());
        dumpLagouResult(result);
    }
}
