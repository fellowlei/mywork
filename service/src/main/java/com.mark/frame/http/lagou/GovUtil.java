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
 * Created by root on 17-3-20.
 * get gov.cn page
 */
public class GovUtil {
    /**
     * test
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
//            getContent("http://www.gov.cn/xinwen/2017-03/05/content_5173604.htm");
        String mainUrl = "http://www.gov.cn/xinwen/index.htm";
        Map<String,String> linkMap =getMainPageLink(mainUrl);
        for(String url: linkMap.keySet()){
            String title = linkMap.get(url);
            System.out.println(url);
            System.out.println(title);
            System.out.println("--------------------");
            getContent(url);
            Thread.sleep(1000);
        }
    }

    /**
     * get link map
     * @param mainUrl
     * @return
     * @throws IOException
     */
    public static Map<String,String> getMainPageLink(String mainUrl) throws IOException {
        Map<String,String> map =new HashMap<String, String>();
        String result =DefaultCookieUtil.get("http://www.gov.cn/xinwen/index.htm");
        Document doc =Jsoup.parse(result);
        Elements elements = doc.getElementsByTag("a");
        for(Element elem : elements){
            String href = elem.attr("href");
            if(href.startsWith("http://www.gov.cn")){
                if(href.matches(".*xinwen.*") && href.matches(".*content.*")){
                    System.out.println(href.trim());
                    getContent(href.trim());
                    map.put(href.trim(),elem.text());
                }
            }
        }
        return map;
    }

    /**
     * get content
     * @param url
     * @throws IOException
     */
    public static void getContent(String url) throws IOException {
            String result = DefaultCookieUtil.get(url);
        Document doc =Jsoup.parse(result);
        Element elem =doc.getElementById("UCAP-CONTENT");
        Elements elements =elem.getElementsByTag("p");
        for(Element e: elements){
            System.out.println(e.text());
        }

//        Pattern pet = Pattern.compile(".*<p.*[^>]>(.*)</p>.*");
//        Matcher match = pet.matcher(html);
//        while (match.find()) {
//            System.out.println(match.group());
//        }
//        String r1 =html.replaceAll("</p>]","");
//        System.out.println(r1);
    }
}
