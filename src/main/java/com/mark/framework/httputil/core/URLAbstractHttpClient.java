package com.mark.framework.httputil.core;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by lulei on 2016/5/6.
 */
public class URLAbstractHttpClient implements HttpMethod {

    public String doPost(String url, Map<String, String> headerMap, Map<String, String> formMap) {
        String response = null;
        try {
            URLConnection conn = new URL(url).openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(1000);
            conn.setReadTimeout(1000);

            // header
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }

            // form
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : formMap.entrySet()) {
                sb.append(entry.getKey() + "=" + entry.getValue() + "&");
            }
            conn.connect();
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(sb.toString());
            out.flush();

            // response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line = null;
            while ((line = in.readLine()) != null) {
                result.append(line + "\n");
            }
            System.out.println(result);
            response = result.toString();
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return response;
    }

    public String listToStr(List<String> list){
        StringBuilder sb = new StringBuilder();
        for(String s: list){
            sb.append(s).append(";");
        }
        return sb.toString();
    }

    public String doGet(String url, Map<String, String> headerMap, Map<String, String> formMap) {
        String response = null;
        // form param
        String urlWithParam = HttpClientUtil.appendParam(url, formMap);
        try {
            URLConnection conn = new URL(urlWithParam).openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(1000);
            conn.setReadTimeout(1000);

            // header
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
            conn.connect();


            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            // response header
            System.out.println("dump\n--------------header-------------------");
            Map<String, List<String>> map = conn.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ":" + listToStr(entry.getValue()));
            }
            System.out.println("---------------------------------------");

            // response body
            StringBuilder result = new StringBuilder();
            String line = null;
            while ((line = in.readLine()) != null) {
                result.append(line + "\n");
            }
            System.out.println("--------------body-------------------");
            System.out.println(result);
            System.out.println("---------------------------------------");
            response = result.toString();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
