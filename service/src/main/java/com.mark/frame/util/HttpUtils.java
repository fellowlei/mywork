package com.mark.frame.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

public class HttpUtils {
    private static final Logger logger = LogManager.getLogger(HttpUtils.class);

    private static final PoolingHttpClientConnectionManager CONNECTION_MANAGER = new PoolingHttpClientConnectionManager();//连接池
    private static final HttpClient HTTP_CLIENT = HttpClients.custom()//
            .setConnectionManager(CONNECTION_MANAGER)//
            .build();//线程安全,可重复使用

    static {
        CONNECTION_MANAGER.setMaxTotal(1000);//最大连接数
        CONNECTION_MANAGER.setDefaultMaxPerRoute(500);//host 对应的最大连接数
    }

    private static final ResponseHandler<String> RESPONSE_HANDLER = new ResponseHandler<String>() {
        @Override
        public String handleResponse(HttpResponse httpResponse) throws IOException {
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            String body = EntityUtils.toString(httpResponse.getEntity());
            if (statusCode == 200) {
                return  body;
            } else {
                logger.error("http execute error code:" + statusCode);
                return null;
            }
        }
    };


    public static String getInfoMap(String url) throws IOException {
//        String url ="http://abc.com/queryUser?id=1";
        return HTTP_CLIENT.execute(new HttpGet(url), RESPONSE_HANDLER);
    }


}
