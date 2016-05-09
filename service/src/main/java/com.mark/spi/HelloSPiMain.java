package com.mark.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by lulei on 2016/4/12.
 */
public class HelloSPiMain {
    public static void main(String[] args) {
        ServiceLoader<Hello> serviceLoader = ServiceLoader.load(Hello.class);
        Iterator<Hello> it = serviceLoader.iterator();
        while(it.hasNext()){
            Hello hello = it.next();
            System.out.println(hello.say());
        }
    }
}
