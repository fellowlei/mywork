package com.mark.frame.http.core;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.List;
import java.util.Map;

/**
 * Created by lulei on 2016/4/28.
 */
public class AbstractHttpClient implements HttpMethod {


    public static void main(String[] args) throws Exception {
//        HttpClientUtil.genMapCode();
    }


    public String doPost(String url, Map<String, String> headerMap, Map<String, String> formMap) {
        String result = null;
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            List<Header> headerList = HttpClientUtil.genHeaders(headerMap);
            httpPost.setHeaders(headerList.toArray(new Header[headerList.size()]));

            List<NameValuePair> formList = HttpClientUtil.genNameValuePairFromMap(formMap);
            httpPost.setEntity(new UrlEncodedFormEntity(formList));

            CloseableHttpResponse response2 = httpclient.execute(httpPost);
            try {
                result = HttpClientUtil.dumpResponse(response2);
            } finally {
                response2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }


    public String doGet(String url, Map<String, String> headerMap, Map<String, String> formMap) {
        String result = null;

        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            url = HttpClientUtil.appendParam(url, formMap);
            HttpGet httpGet = new HttpGet(url);

            CloseableHttpResponse response = httpclient.execute(httpGet);

            try {
                result = HttpClientUtil.dumpResponse(response);
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }


}
