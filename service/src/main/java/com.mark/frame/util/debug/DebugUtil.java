package com.mark.frame.util.debug;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by lulei on 2018/1/18.
 */
public class DebugUtil {
    private static final Log log = LogFactory.getLog(DebugUtil.class);
    private static final LinkedBlockingQueue<DebugBean> blockingQueue = new LinkedBlockingQueue<DebugBean>(5000);
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private static Map<String,String> debugMap = new ConcurrentHashMap<String, String>();
    private boolean stop = false;
    public void add(DebugBean debugBean){
        boolean flag = blockingQueue.offer(debugBean);
        if(!flag){
            log.error("insert debugBean failed" + debugBean);
        }
    }

    public DebugBean get(){
        DebugBean debugBean = blockingQueue.poll();
        return debugBean;
    }

    public void init(){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while(!stop){
                    try {
                        DebugBean debugBean = blockingQueue.poll();
                        if(debugMap.size() > 5000){
                            debugMap = new ConcurrentHashMap<String, String>();
                            log.error("debugMap size > 5000,resize it to 0!");
                        }
                        debugMap.put(debugBean.getId(), debugBean.getDetail());
                    }catch (Exception e){
                        log.error(ExceptionUtils.getStackTrace(e));
                    }
                }
            }
        });
    }

    public String getDebugInfo(){
        Map<String,String> map = debugMap;
        debugMap = new ConcurrentHashMap<String, String>();
        return JSON.toJSONString(map);
    }






}
