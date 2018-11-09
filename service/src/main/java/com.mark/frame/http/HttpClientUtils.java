package com.mark.frame.http;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpClientUtils {
    private static final Logger LOG = LoggerFactory.getLogger(HttpClientUtils.class);
    public static CloseableHttpClient httpClient = null;
    static {

        SocketConfig socketConfig = SocketConfig.custom()
                .setTcpNoDelay(true)
                .setSoReuseAddress(true)
                .setSoTimeout(3000)
                .setSoKeepAlive(true)
                .build();
        /**
         * http pool
         */
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);
        cm.setDefaultSocketConfig(socketConfig);


        /**
         * request请求相关配置
         */
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(3000)
                .setSocketTimeout(3000)
                .setConnectionRequestTimeout(2000)
                .setStaleConnectionCheckEnabled(true)
                .build();

        httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();

        new IdleConnectionMonitorThread(cm).start();
    }

    private static class  IdleConnectionMonitorThread extends Thread{
        private final HttpClientConnectionManager connMgr;
        private volatile boolean shutdown;

        public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr) {
            super();
            this.connMgr = connMgr;
        }
        @Override
        public void run() {
            try {
                while (!shutdown) {
                    synchronized (this) {
                        wait(60000);
                        // Close expired connections
                        connMgr.closeExpiredConnections();
                        // Optionally, close connections
                        // that have been idle longer than 30 sec
                        connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
                    }
                }
            } catch (InterruptedException ex) {
                // terminate
            }
        }

        public void shutdown() {
            shutdown = true;
            synchronized (this) {
                notifyAll();
            }
        }

    }


    /**
     * http get
     * @param url
     * @param params
     * @return
     */
    public static Map<String, Object> get(String url, String params) {
        String urlStr = url + "?" + params.toString();
        for(int i=0; i<3; i++){
            try {
                long startTime = System.currentTimeMillis();
                HttpGet httpGet = new HttpGet(urlStr);
                CloseableHttpResponse response1 = httpClient.execute(httpGet);
                long costTime = System.currentTimeMillis() - startTime;
//                LOG.info("http get cost:{}",costTime);
                HttpEntity entity1 = response1.getEntity();
                String response = EntityUtils.toString(entity1);
                Map<String, Object> result = JSON.parseObject(response, Map.class);
                int responseCode = (Integer) result.get("responseCode");
                if (responseCode != 0) {
                    LOG.error("message send failure, url is: {},response is: ",urlStr,response);
                }else{
                    return result;
                }
            }catch (Exception e){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                LOG.error("http get {} error,url:{},error msg:{}",e.getMessage());
                e.printStackTrace();
            }
        }

        return null;
    }
}
