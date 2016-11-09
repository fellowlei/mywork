package com.mark.gen.analyze;

import com.mark.frame.http.core.DefaultHttpClient;
import com.mark.frame.http.core.HttpClientUtil;
import com.mark.frame.http.core.UserHttpMethod;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lulei on 2016/11/9.
 */
public class LogFromDeployHttpUtil implements UserHttpMethod {
    @Override
    public Map<String, String> getHeaderMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Accept", "*/*");
        map.put("Accept-Encoding", "gzip, deflate");
        map.put("Accept-Language", "zh-CN,zh;q=0.8");
        map.put("Connection", "keep-alive");
        map.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        map.put("Cookie", "");
        map.put("Host", "deploy.mark.com");
        map.put("Origin", "http://deploy.mark.com");
        map.put("Referer", "http://deploy.mark.com/welcome/");
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36");
        map.put("X-Requested-With", "XMLHttpRequest");
        return map;
    }

    @Override
    public Map<String, String> getFormMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "/access.log");
//        map.put("id", "/error.log");
        map.put("encoding", "");
        map.put("head", "false");
        map.put("lines", "100");
        return map;
    }

    @Override
    public String getURL() {
        return "http://deploy.mark.com/tools/viewFile/CCADD2D36CEB6E06ACDCF78CF12E598E57F5AF852EF971C5A18D22B1C4CEDA6FEF22E6373E3128950A32A6FBB206F3D78CA02767678E0DE5";

    }

    public static String dumpResult(String result) throws IOException {
        Element element = Jsoup.parse(result).select("pre.prettyprint").get(0);
//       LineIterator it= IOUtils.lineIterator(IOUtils.toInputStream(element.text()), "utf-8");
//       int i=0;
//        while(it.hasNext()){
//            System.out.println(i + " " + it.next());
//            i++;
//        }
//        it.close();
        return element.text();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        AnalyzeLogUtil util = new AnalyzeLogUtil();
        for(int i=0; i<3; i++){
            String result = DefaultHttpClient.invokePost(new LogFromDeployHttpUtil());
            System.out.println("##########################");
            String log = dumpResult(result);
            util.analyzeByString(log);
            HttpClientUtil.sleep(1000 * 10L);
        }
        util.dumpResut();



    }

}
