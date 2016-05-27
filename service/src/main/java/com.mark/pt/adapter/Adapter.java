package com.mark.pt.adapter;

/**
 * Created by lulei on 2016/5/26.
 */
public class Adapter extends Source implements Targetable {
    @Override
    public void method2() {
        System.out.println("Adapter.method2");
    }

    public static void main(String[] args) {
        Targetable target = new Adapter();
        target.method1();
        target.method2();
    }
}
