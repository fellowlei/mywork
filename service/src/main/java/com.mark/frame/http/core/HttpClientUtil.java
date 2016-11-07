package com.mark.frame.http.core;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lulei on 2016/4/28.
 */
public class HttpClientUtil {

    public static void genMapCode() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Map<String,String> map = new HashMap<String, String>();\n");
        String path = "D:/demo/header.txt";
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] tmp = line.split(":", 2);
            if (tmp.length > 1) {
                tmp[1] = tmp[1].replaceAll("\"", "\\\\\"");
                sb.append("map.put(\"" + tmp[0] + "\",\"" + tmp[1] + "\");\n");
            }
        }
        sb.append("return map;\n");
        System.out.println(sb.toString());
        br.close();
    }

    public static Map<String, String> readHeader() throws IOException {
        String path = "D:/demo/header.txt";
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = null;
        Map<String, String> map = new HashMap<String, String>();
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            String[] tmp = line.split(":", 2);
            if (tmp.length > 1) {
                map.put(tmp[0], tmp[1]);
            }
        }
        System.out.println(map.size());
        br.close();
        return map;
    }

    public static Map<String, String> readForm() throws IOException {
        String path = "D:/demo/form.txt";
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = null;
        Map<String, String> map = new HashMap<String, String>();
        while ((line = br.readLine()) != null) {
            String[] tmp = line.split(":", 2);
            System.out.println(line);
            if (tmp.length > 1) {
                map.put(tmp[0], tmp[1]);
            } else {
                map.put(tmp[0], "");
            }
        }
        System.out.println(map.size());
        br.close();
        return map;

    }

    public static List<NameValuePair> genNameValuePairFromMap(Map<String, String> formMap) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : formMap.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return nvps;
    }

    public static List<Header> genHeaders(Map<String, String> headerMap) {
        List<Header> list = new ArrayList<Header>();
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            list.add(new BasicHeader(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    public static String dumpResponse(CloseableHttpResponse response) throws IOException {
        System.out.println(response.getStatusLine());
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, Charset.forName("UTF-8"));
//        System.out.println(result);
        return result;
    }

    public static void dumpMap(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    public static List<String> readIp(String url) throws IOException {
        List<String> ipList = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(url));
        String line = null;
        while ((line = br.readLine()) != null) {
            ipList.add(line);
//            System.out.println(line);
        }
        br.close();
        return ipList;
    }

    public static String appendParam(String url, Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        if (url.indexOf("?") < 0) {
            sb.append("?");
        } else {
            sb.append("&");
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        if (sb.length() > 0) {
            sb.substring(0, sb.length() - 1);
        }
        return url + sb.toString();
    }

    public static void sleep(Long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printLineByRegex(String msg,String regex) throws IOException {
        BufferedReader bf =  new BufferedReader(new StringReader(msg));
        String line = "";
        while((line = bf.readLine()) != null){
            if(line.matches(regex)){
                System.out.println(line);
            }
        }
    }

}
