package com.mark.auto.testutils.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lulei on 2016/5/20.
 */
public class HostUtils {

    public static String hostFile = "C:/Windows/System32/drivers/etc/hosts";

    public static void dumpHost() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(hostFile));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        System.out.println(sb.toString());
        br.close();
    }

    public static void writeToHost(String host) throws IOException {
        FileWriter fileWriter = new FileWriter(hostFile);
        fileWriter.write(host);
        fileWriter.flush();
        fileWriter.close();
    }

    /**
     * switch host
     * @param host
     * @throws IOException
     */
    public static  void switchHost(String host) throws IOException {
        java.security.Security.setProperty("networkaddress.cache.ttl", "0");
        writeToHost(host);
    }

    public static boolean isHost(String host) {
        if (host != null && !host.trim().equals("") && !host.trim().startsWith("#")) { // 过滤注释
            return true;
        } else {
            return false;
        }
    }

    public static void isValidIP(String ip){
        String regex = "\\d+\\.\\d+\\.\\d+\\.\\d+";
        Matcher m = Pattern.compile(regex).matcher(ip);
        System.out.println(m.find());
        if(m.find()){
            System.out.println(m.group());
        }
    }

    public static List<String> fileToList(String path) throws IOException {
        List<String> list = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = null;
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        br.close();
        return list;
    }

    public void ping(String ip) throws IOException {
//        InputStream is = Runtime.getRuntime().exec("ping ip").getInputStream();

    }


    public static void main(String[] args) throws Exception {
//        List<String> hostList = fileToList("d:/demo/hosts.txt");
//        System.out.println(JSON.toJSON(hostList));
//        for (String host : hostList) {
//            if (isHost(host)) {
//                HostUtils.writeToHost(host);
//                dumpHost();
//                Thread.sleep(5000);
//            }
//        }


    }

}
