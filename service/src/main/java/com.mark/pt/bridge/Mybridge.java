package com.mark.pt.bridge;

/**
 * Created by lulei on 2016/5/26.
 */
public class Mybridge extends Bridge {
    public void method1() {
        getSource().method1();
    }

    public static void main(String[] args) {
        Bridge bridge = new Mybridge();

        Sourceable source1 = new SourceSub1();
        bridge.setSource(source1);
        bridge.method1();
    }
}
