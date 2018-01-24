package com.mark.frame.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by lulei on 2018/1/24.
 */
@Aspect
public class AspectOne {
    @Pointcut("execution(* com.mark.frame.aop.UserDaoImpl.*(..))")
    private void myPointcut(){}


    @Before("myPointcut()")
    public void beforeOne(){
        System.out.println("AspectOne.beforeOne 1");
    }

    @Before("myPointcut()")
    public void beforeTwo(){
        System.out.println("AspectOne.beforeTwo 2");
    }

    @AfterReturning(value = "myPointcut()")
    public void AfterReturningThree(){
        System.out.println("AspectOne.AfterReturningThree 3");
    }

    @AfterReturning(value = "myPointcut()")
    public void AfterReturningFour(){
        System.out.println("AspectOne.AfterReturningFour 4");
    }
}
