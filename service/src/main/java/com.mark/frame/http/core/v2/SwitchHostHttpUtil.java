package com.mark.frame.http.core.v2;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lulei on 2017/11/10.
 */
public class SwitchHostHttpUtil {
    private static String buildURL(String uri, String ip) throws MalformedURLException {
        URL url = new URL(uri);
//        URL newURL = new URL(url.getProtocol(),ip,url.getPath());
//        System.out.println(newURL + "?" +  url.getQuery());
        return url.getProtocol() + "://" + ip + url.getPath() + "?" + url.getQuery();
    }

    public  static  String post(String uri,Map<String,String> header,String ip,String domain) throws IOException {
        String url = buildURL(uri,ip);
        header.put("host",domain);
        return LoginHttpUtil.post(url, header);
    }

    public  static  void posts(String uri,Map<String,String> header,String domain) throws IOException {
        List<String> ips = FileUtils.readLines(new File("d:/demo/ip.txt"));
        for(String ip: ips){
            String result = post(uri,header,ip,domain);
            System.out.println(ip + ":"  +result);
        }
    }


    public  static  String get(String uri,String ip,String domain) throws IOException {
        Map<String,String> header = new HashMap<String, String>();
        String url = buildURL(uri, ip);
        header.put("host",domain);
        return LoginHttpUtil.get(url,header);
    }

    public  static  void gets(String uri,String domain) throws IOException {
        List<String> ips = FileUtils.readLines(new File("d:/demo/ip.txt"));
        for(String ip: ips){
            String result = get(uri, ip, domain);
            System.out.println(ip + ":"  +result);
        }
    }



    public static void main(String[] args) throws Exception{
        gets("http://test.com?a=b","test.com");
    }
}
