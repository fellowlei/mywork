package com.mark.auto.testutils.core;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lulei on 2016/5/24.
 */
public class AbstractURLTestUtil {
    /**
     * 超时时间
     *
     * @param timeoutInMilliseconds
     * @return
     */
    public static RequestConfig requestConfigWithTimeout(int timeoutInMilliseconds) {
        return RequestConfig.copy(RequestConfig.DEFAULT)
                .setSocketTimeout(timeoutInMilliseconds)
                .setConnectTimeout(timeoutInMilliseconds)
                .setConnectionRequestTimeout(timeoutInMilliseconds)
                .build();
    }

    /**
     * 测试url
     *
     * @param url
     */
    public static boolean testURL(String url) {
        CloseableHttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfigWithTimeout(1000)).build();
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            System.out.println(response.getStatusLine() + ":" + EntityUtils.toString(response.getEntity()));
            return true;
        } catch (Exception e) {
            System.err.println("test URL error: " + e + ",url" + url);
            return false;
        }
    }

    public static void switchHostTestURL(String host, String url) throws Exception {
        List<String> ipList = HostUtils.fileToList("d:/demo/ip.txt");
        List<String> errList = new ArrayList<String>();
        for (String ip : ipList) {
            System.out.println("---------------------------------------------------");
            String tmpHost = ip + "  " + host;
            java.security.Security.setProperty("networkaddress.cache.ttl", "0");
            HostUtils.writeToHost(tmpHost);
            HostUtils.dumpHost();
            boolean testResult = testURL(url);
            if (!testResult) {
                errList.add(ip);
            }
            Thread.sleep(500);
        }
        dumpErrorMsg(errList);
    }

    public static void switchHostTestURLs(String host, List<String> urlList) throws Exception {
        List<String> ipList = HostUtils.fileToList("d:/demo/ip.txt");
        List<String> errList = new ArrayList<String>();
        for (String ip : ipList) {
            System.out.println("---------------------------------------------------");
            String tmpHost = ip + "  " + host;
            java.security.Security.setProperty("networkaddress.cache.ttl", "0"); // 请dns缓存
            HostUtils.writeToHost(tmpHost);
            HostUtils.dumpHost();
            for(String url: urlList){
                boolean testResult = testURL(url);
                if (!testResult) {
                    errList.add(ip);
                    break;
                }
            }
            Thread.sleep(500);
        }
        dumpErrorMsg(errList);
    }

    public static void dumpErrorMsg(List<String> errList) {
        System.err.println("##############result#################");
        if (errList.size() == 0) {
            System.err.println("all success");
        } else {
            System.err.println("error size:" + errList.size());
            for (String ip : errList) {
                System.err.println(ip);
            }
        }
        System.err.println("##############result#################");
    }
}
