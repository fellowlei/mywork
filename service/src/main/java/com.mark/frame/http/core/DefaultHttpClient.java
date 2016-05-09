package com.mark.frame.http.core;

import java.util.Map;

/**
 * Created by lulei on 2016/4/28.
 */
public class DefaultHttpClient extends AbstractHttpClient {
    private UserHttpMethod userHttpMethod;

    public DefaultHttpClient(UserHttpMethod userHttpMethod) {
        this.userHttpMethod = userHttpMethod;
    }

    public DefaultHttpClient() {
    }

    public String doPost() {
        return doPost(userHttpMethod.getURL(), userHttpMethod.getHeaderMap(), userHttpMethod.getFormMap());
    }

    public String doGet() {
        return doGet(userHttpMethod.getURL(), userHttpMethod.getHeaderMap(), userHttpMethod.getFormMap());
    }

    public static String invokeDoPost(String url, Map<String, String> headerMap, Map<String, String> formMap) {
        return new DefaultHttpClient().doPost(url, headerMap, formMap);
    }

    public static String invokeDoGet(String url, Map<String, String> headerMap, Map<String, String> formMap) {
        return new DefaultHttpClient().doGet(url, headerMap, formMap);
    }

    public static String invokePost(UserHttpMethod userHttpMethod) {
        return new DefaultHttpClient(userHttpMethod).doPost();
    }

    public static String invokeGet(UserHttpMethod userHttpMethod) {
        return new DefaultHttpClient(userHttpMethod).doGet();
    }

    public static String invokePost(HttpRequest httpRequest) {
        return new DefaultHttpClient().doPost(httpRequest.getUrl(), httpRequest.getHeaderMap(), httpRequest.getParamMap());
    }
}
