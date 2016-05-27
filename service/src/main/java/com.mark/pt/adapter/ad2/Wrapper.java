package com.mark.pt.adapter.ad2;

import com.mark.pt.adapter.Source;
import com.mark.pt.adapter.Targetable;

/**
 * Created by lulei on 2016/5/26.
 */
public class Wrapper implements Targetable {

    private Source source;

    public Wrapper(Source source) {
        this.source = source;
    }

    @Override
    public void method1() {
        source.method1();
    }

    @Override
    public void method2() {
        System.out.println("Wrapper.method2");
    }

    public static void main(String[] args) {
        Source source = new Source();
        Targetable target = new Wrapper(source);
        target.method1();
        target.method2();
    }
}
