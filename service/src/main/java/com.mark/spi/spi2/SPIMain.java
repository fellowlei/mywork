package com.mark.spi.spi2;


import java.util.ServiceLoader;

/**
 * Created by lulei on 2016/4/11.
 */
public class SPIMain {

    public static void main(String[] args) {
        ServiceLoader<SPIProvider> serviceLoader = ServiceLoader.load(SPIProvider.class);
        for (SPIProvider spi : serviceLoader) {
            spi.method();
        }
    }
}
