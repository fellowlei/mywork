package com.mark.frame.call;

import com.alibaba.fastjson.JSONArray;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpProxy {

    private static CloseableHttpClient httpclient = HttpClients.createDefault();


    public static Object invokeHttpProxy(String name, Method method, Object[] args) {
        Map<String, String> data = new HashMap<>();
        data.put("name", name);
        data.put("method", method.getName());
        data.put("args", KryoTool.encode(args));
        String text = sendHttpRequest(data);

        JSONArray array = JSONArray.parseArray(text);
        try {
            Object decode = KryoTool.decode(array.getString(1), Class.forName(array.getString(0)));
            return decode;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("class not found", e);
        }
    }

    public static String sendHttpRequest(Map<String, String> data) {
        for (int i = 0; i < 3; i++) {
            try {
                String ip = "127.0.0.1";
                HttpPost httpPost = new HttpPost("http://" + ip + "/call");
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : data.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(nvps));
                httpPost.setHeader("Host", "abc.com");
                CloseableHttpResponse response = httpclient.execute(httpPost);

                try {
                    HttpEntity entity2 = response.getEntity();
                    return EntityUtils.toString(entity2);
                } finally {
                    response.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("Mock Error");
    }
}
