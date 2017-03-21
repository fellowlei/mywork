package com.mark.frame.http.lagou;

import com.mark.frame.http.core.v2.DefaultCookieUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lulei on 2017/3/21.
 */
public class XinHuaNewsUtil {
    public static void main(String[] args) throws IOException {
        Map<String,String> linkMap = getLinks("http://www.xinhuanet.com/");
        for(String link: linkMap.keySet()){
            System.out.println(linkMap.get(link));
            System.out.println("-------------------");
            System.out.println(link);
            getContent(link);
        }

    }

    public static void getContent(String url) throws IOException {
        String result =DefaultCookieUtil.get(url);
        Element element =Jsoup.parse(result).getElementById("p-detail");
        if(element == null){
            element =Jsoup.parse(result).getElementById("content");
            if(element == null){
                element =Jsoup.parse(result).getElementById("article");
            }
        }
        if(element != null){
            System.out.println(element.text());
        }else{
            System.out.println("############" + url);
        }

    }

    public static Map<String, String> getLinks(String url) throws IOException {
        Map<String,String> linkMap =new HashMap<String, String>();

//        String result =DefaultCookieUtil.get("http://www.xinhuanet.com/");
        String result =DefaultCookieUtil.get(url);
        Document doc =Jsoup.parse(result);
        Elements elements =doc.getElementsByTag("a");
        for(Element element: elements){
            String href = element.attr("href");
            if(href.startsWith("http://news.xinhuanet.com/") && href.matches(".*politics.*")){
//                System.out.println(element.attr("href") + " : " + element.html());
                linkMap.put(href.trim(),element.html());
            }
        }
        return linkMap;
    }
}
