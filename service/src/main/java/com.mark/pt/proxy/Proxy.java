package com.mark.pt.proxy;

/**
 * Created by lulei on 2016/5/26.
 */
public class Proxy implements Sourceable {

    private Sourceable source;

    public Proxy(Sourceable source) {
        this.source = source;
    }

    @Override
    public void method1() {
        System.out.println("before");
        source.method1();
        System.out.println("after");
    }

    public static void main(String[] args) {
        Sourceable source = new Source();
        Sourceable proxy = new Proxy(source);
        proxy.method1();


    }


}
