package com.mark.framework.httputil.core;

import java.util.Map;

/**
 * Created by Administrator on 2016/5/6.
 */
public interface HttpMethod {
    public String doPost(String url, Map<String, String> headerMap, Map<String, String> formMap);

    public String doGet(String url, Map<String, String> headerMap, Map<String, String> formMap);

}

