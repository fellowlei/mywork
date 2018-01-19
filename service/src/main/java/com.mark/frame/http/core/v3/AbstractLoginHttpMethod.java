package com.mark.frame.http.core.v3;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lulei on 2018/1/19.
 * http请求抽象类，提供get，post方法实现
 */
public class AbstractLoginHttpMethod implements LoginHttpMethod {

    public static CloseableHttpClient httpClient;
    public static HttpClientContext context;
    public static CookieStore cookieStore;
    public static RequestConfig requestConfig;

    static {
        init();
    }

    private static void init() {
        context = HttpClientContext.create();
        cookieStore = new BasicCookieStore();
        // 配置超时时间（连接服务端超时2秒，请求数据返回超时2秒）
        requestConfig = RequestConfig.custom().setConnectTimeout(2000).setSocketTimeout(2000)
                .setConnectionRequestTimeout(2000).build();
        // 设置默认跳转以及存储cookie
        httpClient = HttpClientBuilder.create().setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setRedirectStrategy(new DefaultRedirectStrategy()).setDefaultRequestConfig(requestConfig)
                .setDefaultCookieStore(cookieStore).build();

    }


    @Override
    public String post(String url, Map<String, String> paramMap) throws IOException{
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = toListParam(paramMap);
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

        List<Header> headerList = genHeaderList(genHeader(""));
        httpPost.setHeaders(headerList.toArray(new Header[headerList.size()]));
        CloseableHttpResponse response = httpClient.execute(httpPost, context);

        try {
            dumpCookies();
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            return result;
        } finally {
            response.close();
        }
    }

    @Override
    public String post(String url, String params)  throws IOException{
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = toNameValuePairList(params);
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

        List<Header> headerList = genHeaderList(genHeader(""));
        httpPost.setHeaders(headerList.toArray(new Header[headerList.size()]));
        CloseableHttpResponse response = httpClient.execute(httpPost, context);

        try {
            dumpCookies();
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            return result;
        } finally {
            response.close();
        }
    }

    public String get(String url) throws IOException{
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet, context);
        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
        try {
//            dumpCookies();
            return result;
        } finally {
            response.close();
        }
    }

    public  String get(String url,Map<String,String> headerMap) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        List<Header> headerList = genHeaderList(headerMap);
        httpGet.setHeaders(headerList.toArray(new Header[headerList.size()]));
        CloseableHttpResponse response = httpClient.execute(httpGet, context);
        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
        try {
//            dumpCookies();
            return result;
        } finally {
            response.close();
        }
    }

    // headerMap to headerList
    public List<Header> genHeaderList(Map<String, String> headerMap) {
        List<Header> list = new ArrayList<Header>();
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            list.add(new BasicHeader(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    // show cookie data
    public void dumpCookies() {
        System.out.println("---------------cookies----------------------");
        cookieStore = context.getCookieStore();
        List<Cookie> cookies = cookieStore.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("key:" + cookie.getName() + "  value:" + cookie.getValue());
        }
    }

    // string param to paramList  eg: a=1&b=2
    public List<NameValuePair> toNameValuePairList(String parameters) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        String[] paramList = parameters.split("&");
        for (String param : paramList) {
            String[] tmp = param.split("=");
            if (tmp.length == 2) {
                nvps.add(new BasicNameValuePair(tmp[0], tmp[1]));
            }
        }
        return nvps;
    }


    // gen default heder
    public Map<String, String> genHeader(String host) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
        if(StringUtils.isNoneEmpty(host)){
            map.put("HOST",host);
        }
        return map;
    }

    // paramMap to paramList
    private List<NameValuePair> toListParam(Map<String, String> paramMap) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for(String key : paramMap.keySet()){
            nvps.add(new BasicNameValuePair(key,paramMap.get(key)));
        }
        return nvps;
    }
}
