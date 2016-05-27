package com.mark.pt.sinleton;

/**
 * Created by lulei on 2016/5/26.
 */
public class Singleton3 {
    private static Singleton3 instance = null;

    private Singleton3() {
    }

    private static synchronized void syncInit() {
        instance = new Singleton3();
    }

    public static Singleton3 getInstance() {
        if (instance == null) {
            syncInit();
        }
        return instance;
    }


}
