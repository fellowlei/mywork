package com.mark.frame.aop;

/**
 * Created by lulei on 2018/1/22.
 */
public class AopMethodImpl1 implements AopMethod {

    @Override
    public void init() {
        System.out.println("AopMethodImpl1.init");
    }

    @Override
    public void build() {
        System.out.println("AopMethodImpl1.build");
    }
}
