package com.mark.frame.http.admcache;

import com.mark.frame.http.core.DefaultHttpClient;
import com.mark.frame.http.core.HttpClientUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by lulei on 2016/11/7.
 */
public class AdmCacheUtil {



    public static void main(String[] args) throws IOException {
        String name = "nginx_pin_mjq";
        List<String> ids =  AdmCacheQueryByName.queryByName(name);
        for(String id: ids){
            Map<String,String>  map = AdmCacheQueryInstance.queryInstanceById(id);
            for(Map.Entry<String,String> entry: map.entrySet()){
                String key = entry.getKey();
                String result = AdmCacheConsoleUtil.executeAdmCache(id,key);
                HttpClientUtil.printLineByRegex(result,".*used_memory_peak_human.*");
            }
        }
        String result = DefaultHttpClient.invokeGet(new AdmCacheQueryByName(name));
//        System.out.println(result);
        AdmCacheQueryByName.parseJson(result);
    }
}
