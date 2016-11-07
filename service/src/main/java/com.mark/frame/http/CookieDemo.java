package com.mark.frame.http;

import com.mark.frame.http.core.HttpClientUtil;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lulei on 2016/11/7.
 */
public class CookieDemo {

    public static void cookiePost(){
        String url="http://adm.cache.mark.com/clusters/1698/instances.json";
        String result = null;
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
            httpPost.setHeader("Accept-Encoding","gzip, deflate, sdch");
            httpPost.setHeader("Accept-Language","zh-CN,zh;q=0.8");
            httpPost.setHeader("Connection","keep-alive");
            httpPost.setHeader("Cookie", "");
            httpPost.setHeader("Host","mmarkos.mark.com");
            httpPost.setHeader("Referer","http://mmarkos.mark.com/monitor/chart?ip=172.22.24.44");
            httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36");
            httpPost.setHeader("X-Requested-With", "XMLHttpRequest");

            List<NameValuePair> formList = new ArrayList<NameValuePair>();
            formList.add(new BasicNameValuePair("ip","172.22.24.44"));
            formList.add(new BasicNameValuePair("period","0.5"));
            formList.add(new BasicNameValuePair("t","93d0d198d7161a3f014cb347b1c0e309"));
            formList.add(new BasicNameValuePair("type","3"));
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
    }

    public static void cookieGet(){
        String url="http://mmarkos.mark.com/monitor/d?ip=172.22.24.44&period=0.5&t=009e66391cfc32865131590f404be344&type=3";
        String result = null;
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
            httpGet.setHeader("Accept-Encoding","gzip, deflate, sdch");
            httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.8");
            httpGet.setHeader("Connection","keep-alive");
            httpGet.setHeader("Cookie","");
            httpGet.setHeader("Host","mmarkos.mark.com");
            httpGet.setHeader("Referer","http://mmarkos.mark.com/monitor/chart?ip=172.22.24.44");
            httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36");
            httpGet.setHeader("X-Requested-With", "XMLHttpRequest");

            CloseableHttpResponse response2 = httpclient.execute(httpGet);
            try {
                result = HttpClientUtil.dumpResponse(response2);
            } finally {
                response2.close();
            }
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        cookieGet();
    }
}
