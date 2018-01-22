package com.mark.frame.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by lulei on 2018/1/22.
 */
public class AopAspect {
    public void before(){
        System.out.println("AopAspect.before");
    }

    public void after(){
        System.out.println("AopAspect.after");
    }

    //around通知方法
    public void around(ProceedingJoinPoint jpoint) {

        try {
            System.out.println(getClass().toString() + " around before show");
            //执行目标对象的连接点处的方法
            jpoint.proceed();
            System.out.println(getClass().toString() + " around after show");
        } catch (Throwable e) {
            System.out.println(getClass().toString() + " around afterThrowing show");
        }
    }


}
