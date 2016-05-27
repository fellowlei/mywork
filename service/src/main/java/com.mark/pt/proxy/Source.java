package com.mark.pt.proxy;

/**
 * Created by lulei on 2016/5/26.
 */
public class Source implements  Sourceable {
    @Override
    public void method1() {
        System.out.println("Source.method1");
    }
}
