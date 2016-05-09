package com.mark.spi.spi2;

/**
 * Created by lulei on 2016/4/11.
 */
public class TestSPIProviderImpl implements SPIProvider {
    @Override
    public void method() {
        System.out.println("TestSPIProviderImpl.method");
    }
}
