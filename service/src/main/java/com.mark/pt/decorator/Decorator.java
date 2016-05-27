package com.mark.pt.decorator;

/**
 * Created by lulei on 2016/5/26.
 */
public class Decorator implements Sourceable {

    private Sourceable source;

    public Decorator(Sourceable source) {
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
        Sourceable obj = new Decorator(source);
        obj.method1();
    }
}
