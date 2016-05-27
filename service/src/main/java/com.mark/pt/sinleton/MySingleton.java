package com.mark.pt.sinleton;

/**
 * Created by lulei on 2016/5/26.
 */
public class MySingleton {
    private MySingleton() {
    }

    private static class MySinletonFactory {
        private static MySingleton instance = new MySingleton();
    }

    public static MySingleton getInstance() {
//        return MySinletonFactory.instance;
        if (instance == null) {
            syncInit();
        }
        return instance;
    }

    private static MySingleton instance = null;

    private static synchronized void syncInit() {
        if (instance == null) {
            instance = new MySingleton();
        }
    }


}
