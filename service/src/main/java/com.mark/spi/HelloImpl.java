package com.mark.spi;

/**
 * Created by lulei on 2016/4/12.
 */
public class HelloImpl implements Hello {
    @Override
    public String say() {
        return "hello world";
    }
}
