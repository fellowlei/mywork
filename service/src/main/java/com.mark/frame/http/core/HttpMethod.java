package com.mark.frame.http.core;

import java.util.Map;

/**
 * Created by lulei on 2016/4/28.
 */
public interface HttpMethod {
    public String doPost(String url, Map<String, String> headerMap, Map<String, String> formMap);

    public String doGet(String url, Map<String, String> headerMap, Map<String, String> formMap);

}
