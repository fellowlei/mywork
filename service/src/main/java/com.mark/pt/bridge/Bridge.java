package com.mark.pt.bridge;

/**
 * Created by lulei on 2016/5/26.
 */
public class Bridge {

    private Sourceable source;

    public void method1() {
        source.method1();
    }

    public Sourceable getSource() {
        return source;
    }

    public void setSource(Sourceable source) {
        this.source = source;
    }
}
