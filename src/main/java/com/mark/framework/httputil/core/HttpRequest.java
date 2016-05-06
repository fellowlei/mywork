package com.mark.framework.httputil.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/6.
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

