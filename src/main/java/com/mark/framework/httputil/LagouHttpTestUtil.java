package com.mark.framework.httputil;

/**
 * Created by Administrator on 2016/5/6.
 */


import com.alibaba.fastjson.JSON;
import com.mark.framework.httputil.core.DefaultHttpClient;
import com.mark.framework.httputil.core.HttpClientUtil;
import com.mark.framework.httputil.core.HttpRequest;
import com.mark.framework.httputil.core.UserHttpMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lulei on 2016/4/29.
 */
public class LagouHttpTestUtil implements UserHttpMethod {
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

    public Map<String, String> getFormMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("first", "false");
        map.put("pn", "1");
        map.put("kd", "Java");
        return map;
    }

    public String getURL() {
        return "http://www.lagou.com/jobs/positionAjax.json?city=%E5%8C%97%E4%BA%AC";
    }

//    public static void dumpLagouResult(LagouResult lagouResult) {
//        System.out.println("--------------------------------------------------");
//        List<Result> results = lagouResult.getContent().getResult();
//        for (Result result : results) {
//            System.out.println(FastJsonUtils.toJson(result));
//        }
//    }

    public static void dumpLagouResult2(String result) {
        System.out.println("--------------------------------------------------");
        Map<String, Object> map = JSON.parseObject(result, Map.class);
        Map<String, Object> map2 = (Map<String, Object>) map.get("content");
        List<Map> list = (List) map2.get("result");
        for (Map map3 : list) {
            System.out.println(JSON.toJSONString(map3));
        }
    }

    public static void batchQuery() {
        LagouHttpTestUtil lagouHttpTestUtil = new LagouHttpTestUtil();
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setUrl(lagouHttpTestUtil.getURL());
        httpRequest.setHeaderMap(lagouHttpTestUtil.getHeaderMap());
        httpRequest.setParamMap(lagouHttpTestUtil.getFormMap());

        for (int i = 1; i < 10; i++) {
            httpRequest.getParamMap().put("pn", i + "");
            String result = DefaultHttpClient.invokePost(httpRequest);
            dumpLagouResult2(result);
//            LagouResult lagouResult = JSON.parseObject(result, LagouResult.class);
//            dumpLagouResult(lagouResult);
            HttpClientUtil.sleep(1000L);
        }
    }

    public static void query() {
        LagouHttpTestUtil lagouHttpTestUtil = new LagouHttpTestUtil();
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setUrl(lagouHttpTestUtil.getURL());
        httpRequest.setHeaderMap(lagouHttpTestUtil.getHeaderMap());
        httpRequest.setParamMap(lagouHttpTestUtil.getFormMap());
        String result = DefaultHttpClient.invokePost(httpRequest);
        dumpLagouResult2(result);
    }

    public static void main(String[] args) throws IOException {

//        query();
//        HttpClientUtil.genMapCode();
        batchQuery();

    }

}

