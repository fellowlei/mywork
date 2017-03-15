package com.mark.frame.http.core.v2;

import com.mark.frame.http.core.HttpClientUtil;
import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.*;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lulei on 2017/3/14.
 */
public class SessionHttpUtil {
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

    public static void main(String[] args) throws Exception {
        loginPost("http://test.com/manager", "action=show");
        get("http://test.com/show?word=user1&page=1");
    }

    public static Map<String, String> genHeader() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        return map;
    }

    public static String loginPost(String url, Map<String,String> paramMap) throws IOException{
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = toListParam(paramMap);
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

        List<Header> headerList = HttpClientUtil.genHeaders(genHeader());
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

    private static List<NameValuePair> toListParam(Map<String, String> paramMap) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for(String key : paramMap.keySet()){
            nvps.add(new BasicNameValuePair(key,paramMap.get(key)));
        }
        return nvps;
    }


    public static String loginPost(String url, String params) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = toNameValuePairList(params);
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

        List<Header> headerList = HttpClientUtil.genHeaders(genHeader());
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

    public static String get(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
//        List<Header> headerList = com.mark.frame.http.core.HttpClientUtil.genHeaders(genHeader());
//        httpGet.setHeaders(headerList.toArray(new Header[headerList.size()]));
        CloseableHttpResponse response = httpClient.execute(httpGet, context);
        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
        try {
//            dumpCookies();
            return result;
        } finally {
            response.close();
        }
    }

    public static void dumpCookies() {
        System.out.println("---------------cookies----------------------");
        cookieStore = context.getCookieStore();
        List<Cookie> cookies = cookieStore.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("key:" + cookie.getName() + "  value:" + cookie.getValue());
        }
    }

    public static List<NameValuePair> toNameValuePairList(String parameters) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        String[] paramList = parameters.split("&");
        for (String param : paramList) {
            String[] tmp = param.split("=");
            if (tmp.length == 2) {
                nvps.add(new BasicNameValuePair(tmp[0], tmp[1]));
            }
        }
//        System.out.println(nvps.toString());
        return nvps;
    }

    /**
     * 添加自定义cookie
     * @param name
     * @param value
     * @param domain
     * @param path
     */
    public static void addCookie(String name, String value, String domain, String path) {
        BasicClientCookie cookie = new BasicClientCookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookieStore.addCookie(cookie);
    }

    public static void printResponse(HttpResponse httpResponse) throws IOException {
        // 获取响应消息实体
        HttpEntity entity = httpResponse.getEntity();
        // 响应状态
        System.out.println("status:" + httpResponse.getStatusLine());
        System.out.println("headers:");
        HeaderIterator iterator = httpResponse.headerIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        String result = EntityUtils.toString(entity);
        System.out.println(result);
    }

    /**
     * 包含cookie
     * @param key
     * @return
     */
    public static boolean containCookie(String key) {
        cookieStore = context.getCookieStore();
        List<Cookie> cookies = cookieStore.getCookies();
        boolean result = false;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key)) {
                result = true;
                break;
            }
        }
        return result;
    }


}
