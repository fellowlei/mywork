package com.mark.frame.http.core.v2;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lulei on 2017/3/14.
 */
public class DefaultCookieUtil {
    public static CloseableHttpClient httpClient;
    public static RequestConfig requestConfig;
    public static String COOKIE_VAL = ""; // 保持状态，cookie必须设置

    static{
        // 配置超时时间（连接服务端超时2秒，请求数据返回超时2秒）
        int timeout=3000;
        requestConfig = RequestConfig.custom().setConnectTimeout(timeout).setSocketTimeout(timeout)
                .setConnectionRequestTimeout(timeout).build();
        // 设置默认跳转以及存储cookie
        httpClient = HttpClientBuilder.create().setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setRedirectStrategy(new DefaultRedirectStrategy()).setDefaultRequestConfig(requestConfig).build();
    }

    public static void setCookie(String cookie){
        COOKIE_VAL = cookie;
    }

    public static String post(String url, String params) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = SessionHttpUtil.toNameValuePairList(params); // 添加变量
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        httpPost.setHeader(new BasicHeader("Cookie",COOKIE_VAL)); // 设置Cookie
        CloseableHttpResponse response = httpClient.execute(httpPost);

        try {
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(result);
            return result;
        } finally {
            response.close();
        }

    }

    public static String post(String url, Map<String,String> paramMap) throws IOException{
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = toListParam(paramMap);
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

        httpPost.setHeader(new BasicHeader("Cookie", COOKIE_VAL)); // 设置Cookie
        CloseableHttpResponse response = httpClient.execute(httpPost);

        try {
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(result);
            return result;
        } finally {
            response.close();
        }
    }

    private static List<NameValuePair> toListParam(Map<String, String> paramMap) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for(String key : paramMap.keySet()){
            nvps.add(new BasicNameValuePair(key,paramMap.get(key)));
        }
        return nvps;
    }

    public static String get(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        httpGet.setHeader(new BasicHeader("Cookie",COOKIE_VAL)); // 设置Cookie
        try {
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
//            System.out.println(result);
            return result;
        } finally {
            response.close();
        }
    }
}
