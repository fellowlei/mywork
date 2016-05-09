package com.mark.frame.http.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lulei on 2016/4/29.
 */
public class HttpRequest{
    private String url;
    private Map<String,String> paramMap = new HashMap<String, String>();
    private Map<String,String> headerMap = new HashMap<String, String>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }
}
