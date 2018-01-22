package com.mark.frame.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lulei on 2018/1/22.
 */
public class AopTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop.xml");

        AopMethod am = (AopMethod)ctx.getBean("aopMethodImpl1");
        AopMethod am2 = (AopMethod)ctx.getBean("aopMethodImpl2");
        am.init();
        System.out.println();
        am.build();

//        System.out.println();
//        am2.init();
//        am2.build();
    }
}
