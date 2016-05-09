package com.mark.auto;


import java.io.*;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lulei on 2016/4/28.
 */
public class HostUtil {

    public static final String hostFilePath = "C:\\Windows\\System32\\drivers\\etc\\hosts";

    public static void addHostBinding(String ip, String host) {
        Map<String, String> hostToIpMap = readFromIpHostFile();
        hostToIpMap.put(host, ip);
        writeIpHostToReader(hostToIpMap);
    }

    public static void deleteHostBinding(String ip, String host) {
        Map<String, String> hostToIpMap = readFromIpHostFile();
        if (hostToIpMap != null && hostToIpMap.containsKey(host)) {
            hostToIpMap.remove(host);
        }
        writeIpHostToReader(hostToIpMap);
    }


    private static void writeIpHostToReader(Map<String, String> hostToIpMap) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(hostFilePath));
            for (Map.Entry entry : hostToIpMap.entrySet()) {
                bw.write(entry.getValue() + " " + entry.getKey());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
            }
        }
    }


    private static Map<String, String> readFromIpHostFile() {
        Map<String, String> hostToIpMap = new HashMap<String, String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(hostFilePath));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tmp = line.split(" ");
                if (tmp != null && tmp.length == 2 && tmp[0] != null && tmp[0].charAt(0) != '#') {
                    hostToIpMap.put(tmp[1], tmp[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
            }
        }
        return hostToIpMap;
    }

    public static String getResponseText(String queryURL) {
        InputStream is = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();

        try {
            URL url = new URL(queryURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setConnectTimeout(2000);

            is = httpURLConnection.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
            }
        }
        return null;
    }


//    public static void jdkDnsNoCacheOnList() throws IOException {
//        java.security.Security.setProperty("networkaddress.cache.ttl" , "0");
//        java.security.Security.setProperty("networkaddress.cache.negative.ttl" , "0");
//
//        Process processTest = Runtime.getRuntime().exec("/etc/init.d/nscd restart");
//        addHostBinding(ip,host);
//        String res = getResponseText(url);
//        Process process = Runtime.getRuntime().exec("/etc/init.d/nscd restart");
//        deleteHostBinding(ip,host);
//        System.out.println();
//    }

    public static void jdkDnsNoCache() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Class clazz = InetAddress.class;
        final Field cacheField = clazz.getDeclaredField("addressCache");
        cacheField.setAccessible(true);
        final Object o = cacheField.get(clazz);
        Class clazz2 = o.getClass();
        final Field cacheMapField = clazz2.getDeclaredField("cache");
        cacheMapField.setAccessible(true);
        final Map cacheMap = (Map) cacheMapField.get(o);
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                try {
                    synchronized (o) {//同步是必须的,因为o可能会有多个线程同时访问修改。
                        cacheMap.clear();//这步比较关键，用于清除原来的缓存
                        cacheMap.remove("www.baidu.com");
                    }
                } catch (Throwable te) {
                    throw new RuntimeException(te);
                }
                return null;
            }
        });
    }

    public static void test() throws Exception {
        addHostBinding("127.0.0.1", "www.baidu.com");
        System.err.println(getResponseText("http://www.baidu.com"));

        System.err.println("************************************************************");
        deleteHostBinding("127.0.0.1", "www.baidu.com");
        try {
            jdkDnsNoCache();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        InetAddress address = InetAddress.getByName("www.baidu.com");
        System.out.println(System.currentTimeMillis() + ":::" + address.getHostAddress());
        System.err.println(getResponseText("http://www.baidu.com"));
    }

    public static void main(String[] args) throws Exception {
//        java.security.Security.setProperty("networkaddress.cache.ttl", "0");
//        java.security.Security.setProperty("networkaddress.cache.negative.ttl", "0");
        test();
    }
}
