package com.mark.frame.auto.test;

/**
 * Created by lulei on 2017/3/6.
 */
public class OneHello implements Hello {
    @Override
    public void sayHello() {
//        System.out.println("hello 1");
        throw new RuntimeException("run error");
    }
}
