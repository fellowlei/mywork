package com.mark.frame.http.core.v3;

import java.io.IOException;
import java.util.Map;

/**
 * Created by lulei on 2018/1/19.
 * 默认实现类
 */
public class DefautlLoginHttpMethod  implements LoginHttpMethod {

    private AbstractLoginHttpMethod abstractLoginHttpMethod = new AbstractLoginHttpMethod();
    @Override
    public String post(String url, Map<String, String> paramMap) throws IOException {
        return abstractLoginHttpMethod.post(url, paramMap);
    }

    @Override
    public String post(String url, String params) throws IOException {
        return abstractLoginHttpMethod.post(url, params);
    }

    @Override
    public String get(String url) throws IOException {
        return abstractLoginHttpMethod.get(url);
    }

    @Override
    public String get(String url, Map<String, String> headerMap) throws IOException {
        return abstractLoginHttpMethod.get(url, headerMap);
    }
}
