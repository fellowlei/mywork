package com.mark.frame.http.core.v3;

import java.io.IOException;
import java.util.Map;

/**
 * Created by lulei on 2018/1/19.
 * http请求接口
 */
public interface LoginHttpMethod {

    String post(String url, Map<String,String> paramMap) throws IOException ;

    String post(String url, String params) throws IOException ;

    String get(String url) throws IOException ;

    String get(String url,Map<String,String> headerMap) throws IOException;

}
