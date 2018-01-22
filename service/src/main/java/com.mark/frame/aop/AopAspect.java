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

    //around֪ͨ����
    public void around(ProceedingJoinPoint jpoint) {

        try {
            System.out.println(getClass().toString() + " around before show");
            //ִ��Ŀ���������ӵ㴦�ķ���
            jpoint.proceed();
            System.out.println(getClass().toString() + " around after show");
        } catch (Throwable e) {
            System.out.println(getClass().toString() + " around afterThrowing show");
        }
    }


}
