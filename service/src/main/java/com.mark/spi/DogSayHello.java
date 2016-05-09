package com.mark.spi;

/**
 * Created by lulei on 2016/4/12.
 */
public class DogSayHello implements Hello {
    @Override
    public String say() {
        return "wang wang!!";
    }
}
