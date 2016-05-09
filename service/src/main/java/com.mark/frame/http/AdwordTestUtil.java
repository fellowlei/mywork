package com.mark.frame.http;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import com.mark.frame.http.core.DefaultHttpClient;
import com.mark.frame.http.core.UserHttpMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lulei on 2016/5/6.
 */
public class AdwordTestUtil implements UserHttpMethod {
    @Override
    public Map<String, String> getHeaderMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Accept", "*/*");
        map.put("Accept-Encoding", "gzip, deflate, sdch");
        map.put("Accept-Language", "zh-CN,zh;q=0.8");
        map.put("Connection", "keep-alive");
        map.put("Host", "ad.3.cn");
        map.put("Referer", "http://search.jd.com/Search?keyword=%E5%BE%AE%E6%9C%8D%E5%8A%A1&enc=utf-8&pvid=qwl5ivni.r3c6a2");
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36");
        return map;

    }

    @Override
    public Map<String, String> getFormMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("skuids", "AD_11826753,AD_11870213,AD_11684053,AD_30189972,AD_10267415806,AD_10111015099,AD_10058693824,AD_11799855,AD_10268702925,AD_10079115862,AD_10110983267,AD_10114214816,AD_10267461168,AD_10127727801,AD_11909216,AD_1133074466,AD_10267454552,AD_1126680311,AD_10096594466,AD_1019707,AD_10268702479,AD_10002400127,AD_10128978129,AD_10122918134,AD_1334342630,AD_1809271850,AD_1580931063,AD_1811136883,AD_1787830508,AD_1637326015,AD_1311477951");
//        map.put("callback","jQuery1757314");
        map.put("_", "1462526305862");
        return map;
    }

    @Override
    public String getURL() {
        return "http://ad.3.cn/ads/mgets";
    }

    public static void dumpAdword(String result) throws ParseException {
        List<Map<String, String>> list = (List<Map<String, String>>) JSON.parse(result);
        for (Map<String, String> map : list) {
            String id = map.get("id");
            String ad = map.get("ad");
            System.out.println(id + "=" + ad);
        }
    }


    public static void main(String[] args) throws Exception {
//        HttpClientUtil.genMapCode();
        AdwordTestUtil adwordTestUtil = new AdwordTestUtil();
        String result = DefaultHttpClient.invokeDoGet(adwordTestUtil.getURL(), adwordTestUtil.getHeaderMap(), adwordTestUtil.getFormMap());
//        System.out.println(result);
        dumpAdword(result);
    }
}
