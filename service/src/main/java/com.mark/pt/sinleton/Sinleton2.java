package com.mark.pt.sinleton;

/**
 * Created by lulei on 2016/5/26.
 */
public class Sinleton2 {
    private Sinleton2() {
    }

    private static class SingletonFactory {
        private static Sinleton2 instance = new Sinleton2();
    }

    public static Sinleton2 getInstance() {
        return SingletonFactory.instance;
    }
}
