package com.mark.pt.sinleton;

/**
 * Created by lulei on 2016/5/25.
 */
public class Singleton {
    private Singleton() {
    }

    private static class SinletonFactory {
        private static Singleton instance = new Singleton();
    }


    public static Singleton getInstance() {
        return SinletonFactory.instance;
    }

    public Object readResolve() {
        return getInstance();
    }
}
