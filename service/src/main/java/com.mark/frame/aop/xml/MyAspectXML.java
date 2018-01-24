package com.mark.frame.aop.xml;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by lulei on 2018/1/24.
 */
public class MyAspectXML {

    public void before(){
        System.out.println("MyAspectXML.before");
    }

    public void afterReturn(Object val){
        System.out.println("MyAspectXML.afterReturn: " + val);
    }

    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("MyAspectXML.around before");
        Object obj = joinPoint.proceed();
        System.out.println("MyAspectXML.around after");
        return obj;
    }

    public void afterThrowing(Throwable throwable){
        System.out.println("MyAspectXML.afterThrowing");
    }


    public void after(){
        System.out.println("MyAspectXML.after");
    }

}
