package com.mark.gen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lulei on 2016/11/8.
 */
public class AnalyzeLogUtil {

    private Map<Integer, AtomicInteger> map = new HashMap<Integer, AtomicInteger>();
    //        Pattern pattern =  Pattern.compile(".*skuids=([^&]*)&origin=(.*)");
    private Pattern pattern = Pattern.compile(".*skuids=([^&]*)");

    public String match(String line) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String skuids = matcher.group(1);
            if (skuids.endsWith(",")) {
                skuids = skuids.substring(0, skuids.length() - 1);
            }
            return skuids;
        }
//        System.out.println(line);
        return "";
    }

    public void dumpResut() {
        System.out.println("###################################");
        Integer totalNum = 0;
        for (Map.Entry<Integer, AtomicInteger> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
            totalNum = totalNum + entry.getValue().get();
        }
        System.out.println("total analyzed: " + totalNum);
    }

    public void analyze() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("d:/demo/log.txt"));
        String line = null;
        while ((line = br.readLine()) != null) {
//            System.out.println(line);
            line = line.replace("%2C",",");
            String skuids = match(line);
            if(skuids == ""){
                continue;
            }
            String[] arr = skuids.split(",");
            Integer key = arr.length;

            if (map.containsKey(key)) {
                map.get(key).incrementAndGet();
            } else {
                map.put(key, new AtomicInteger(1));
            }
        }
        br.close();

        dumpResut();

    }

    public void printLine() throws IOException {
        Integer num = 0;
        BufferedReader br = new BufferedReader(new FileReader("d:/demo/log.txt"));
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            num++;
        }
        br.close();
        System.out.println("total line : " + num);
    }


    public static void main(String[] args) throws IOException {
        AnalyzeLogUtil util = new AnalyzeLogUtil();
//        util.printLine();
        util.analyze();

    }
}
