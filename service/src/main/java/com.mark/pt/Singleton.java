package com.mark.pt;

/**
 * Created by lulei on 2016/4/18.
 */
public class Singleton {

    private Singleton() {
    }

    private static class SingletonHolder {
        private static Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }

    public void method1() {
        System.out.println("Singleton.method1");
    }

    public static void main(String[] args) {
        Singleton.getInstance().method1();
    }
}
