package com.mark.framework.httputil.core;

/**
 * Created by Administrator on 2016/5/6.
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

