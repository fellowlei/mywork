package com.mark.frame.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by lulei on 2018/1/24.
 */
@Aspect
public class MyAspect {
    /**
     * 前置通知
     */
    @Before("execution(* com.mark.frame.aop.UserDao.addUser(..))")
    public void before(){
        System.out.println("MyAspect.before");
    }



    /**
     * 后置通知
     * returnVal,切点方法执行后的返回值
     */
    @AfterReturning(value = "execution(* com.mark.frame.aop.UserDao.addUser(..))",returning = "val")
    public void afterRetuning(Object val){
        System.out.println("MyAspect.afterRetuning: " + val);
    }

    /**
     * 环绕通知
     * @param joinPoint 可用于执行切点的类
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.mark.frame.aop.UserDao.addUser(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("MyAspect.around before");
        Object obj = joinPoint.proceed();
        System.out.println("MyAspect.around after");
        return obj;
    }

    /**
     * 抛出通知
     * @param e
     */
    @AfterThrowing(value="execution(* com.mark.frame.aop.UserDao.addUser(..))",throwing = "e")
    public void afterThrowable(Throwable e){
        System.out.println("MyAspect.afterThrowable: " + e.getMessage());
    }

    /**
     * 无论什么情况下都会执行的方法
     */
    @After(value="execution(* com.mark.frame.aop.UserDao.addUser(..))")
    public void after(){
        System.out.println("MyAspect.after");
    }


}
