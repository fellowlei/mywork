package com.mark.frame.http;

import com.alibaba.fastjson.JSON;
import com.mark.frame.http.core.DefaultHttpClient;
import com.mark.frame.http.core.UserHttpMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lulei on 2016/5/6.
 */
public class PriceTestUtil implements UserHttpMethod {
    @Override
    public Map<String, String> getHeaderMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Accept", "*/*");
        map.put("Accept-Encoding", "gzip, deflate, sdch");
        map.put("Accept-Language", "zh-CN,zh;q=0.8");
        map.put("Connection", "keep-alive");
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36");
        return map;
    }

    @Override
    public Map<String, String> getFormMap() {
        Map<String, String> map = new HashMap<String, String>();
//        map.put("callback", "jQuery4268859");
        map.put("type", "1");
        map.put("area", "1_72_2799_0");
        map.put("skuIds", "J_11826753,J_11870213,J_11684053,J_30189972,J_10267415806,J_10111015099,J_10058693824,J_11799855,J_10268702925,J_10079115862,J_10110983267,J_10114214816,J_10267461168,J_10127727801,J_11909216,J_1133074466,J_10267454552,J_1126680311,J_10096594466,J_1019707,J_10268702479,J_10002400127,J_10128978129,J_10122918134,J_1334342630,J_1809271850,J_1580931063,J_1811136883,J_1787830508,J_1637326015,J_1311477951");
        map.put("pdbp", "0");
        map.put("pdtk", "");
//        map.put("pdpin", "fellowlei");
//        map.put("pduid", "138024135");
        map.put("_", "1462526305858");
        return map;
    }

    @Override
    public String getURL() {
        return "http://p.3.cn/prices/mgets";
    }

    public static void dumpPrice(String result) {
        List<Map<String, String>> list = (List<Map<String, String>>) JSON.parse(result);
        for (Map<String, String> map : list) {
            String id = map.get("id");
            String p = map.get("p");
            String m = map.get("m");
            System.out.println(id + "=" + "p=" + p + ",m=" + m);
        }
    }

    public static void main(String[] args) throws Exception {
//        HttpClientUtil.genMapCode();

        PriceTestUtil priceTestUtil = new PriceTestUtil();
        String result = DefaultHttpClient.invokeDoGet(priceTestUtil.getURL(), priceTestUtil.getHeaderMap(), priceTestUtil.getFormMap());
//        System.out.println(result);
        dumpPrice(result);
    }
}
