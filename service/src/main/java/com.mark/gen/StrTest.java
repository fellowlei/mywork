package com.mark.gen;

import com.mark.frame.FastJsonUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lulei on 2016/4/13.
 */
public class StrTest {
    public static void main(String[] args) throws Exception {
        stringTest();
    }

    public static void test1(){
        int num = 10;
        System.out.println(num + "=" + Integer.toBinaryString(num));
        num = num << 1;
        System.out.println(num + "=" + Integer.toBinaryString(num));
        num = num << 1;
        System.out.println(num + "=" + Integer.toBinaryString(num));
        num = num >>> 1;
        System.out.println(num + "=" + Integer.toBinaryString(num));
        num = num >>> 1;
        System.out.println(num + "=" + Integer.toBinaryString(num));
        num = num >>> 1;
        System.out.println(num + "=" + Integer.toBinaryString(num));
    }

    public static void test2(){
        System.out.println("=============算术右移 >> ===========");
        int i=0xC0000000;
        System.out.println("移位前：i= "+i+" = "+Integer.toBinaryString(i)+"(B)");
        i=i>>28;
        System.out.println("移位后：i= "+i+" = "+Integer.toBinaryString(i)+"(B)");

        System.out.println("---------------------------------");

        int j=0x0C000000;
        System.out.println("移位前：j= "+j+" = "+Integer.toBinaryString(j)+"(B)");
        j=j>>24;
        System.out.println("移位后：j= "+j+" = "+Integer.toBinaryString(j)+"(B)");

        System.out.println("\n");
        System.out.println("==============逻辑右移 >>> =============");
        int m=0xC0000000;
        System.out.println("移位前：m= "+m+" = "+Integer.toBinaryString(m)+"(B)");
        m=m >>> 28;
        System.out.println("移位后：m= "+m+" = "+Integer.toBinaryString(m)+"(B)");

        System.out.println("---------------------------------");

        int n=0x0C000000;
        System.out.println("移位前：n= "+n+" = "+Integer.toBinaryString(n)+"(B)");
        n=n>>24;
        System.out.println("移位后：n= "+n+" = "+Integer.toBinaryString(n)+"(B)");

        System.out.println("\n");
        System.out.println("==============移位符号的取模===============");
        int a=0xCC000000;
        System.out.println("移位前：a= "+a+" = "+Integer.toBinaryString(a)+"(B)");
        System.out.println("算术右移32：a="+(a>>32)+" = "+Integer.toBinaryString(a>>32)+"(B)");
        System.out.println("逻辑右移32：a="+(a>>>32)+" = "+Integer.toBinaryString(a>>>32)+"(B)");
    }

    public static void stringTest() throws Exception {
//        String s = "http://prr.jd.local/queryMarginIn?pin=user3&token=check&origin=1&skuList=[{%22skuid%22:%22100010%22,%22price%22:%2280%22},{%22skuid%22:%22100011%22,%22price%22:%22120%22}]";
        String json = URLEncoder.encode("[{\"skuid\":\"100010\",\"price\":\"80\"},{\"skuid\":\"100011\",\"price\":\"120\"}]", "UTF-8");
        System.out.println(json);

        StringBuilder sb = new StringBuilder();
        sb.append("http://prr.jd.local/queryMarginIn?pin=user3&token=check&origin=1&skuList=");
        sb.append(json);
        System.out.println(sb.toString());

        //http://prr.jd.local/queryMarginIn?pin=user3&token=check&origin=1&skuList=[{"skuid":"100010","price":"80"},{"skuid":"100011","price":"120"}]
//        Map<String, Object> map = new HashMap<String, Object>();
//        List<Object> list = new ArrayList<Object>();
//        Map<String, Object> map2 = new HashMap<String, Object>();
//        map2.put("skuid", "100010");
//        map2.put("price", "80");
//        list.add(map2);
//        map.put("pin", "user3");
//        map.put("token", "check");
//        map.put("skuList", list);
//        System.out.println(FastJsonUtils.toJson(map));

//        URL url = new URL("http://prr.jd.local/addIn?pin=user3&token=check&origin=1&skuList=[{%22skuid%22:%22100010%22,%22price%22:%22100%22},{%22skuid%22:%22100011%22,%22price%22:%22200%22}]");
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    }
}
