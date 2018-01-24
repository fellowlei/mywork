package com.mark.frame.aop.demo2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Date;

/**
 * Created by lulei on 2018/1/24.
 * AOP实现 缓存，权限验证、内容处理、事务控制
 */
@Aspect
public class ExceptionMonitor {
    /**
     * 定义异常监控类
     */
    @Pointcut("execution(* com.mark.frame.aop.UserDaoImpl.*(..))")
    void exceptionMethod() {
    }

    @Around("exceptionMethod()")
    public Object monitorMethods(ProceedingJoinPoint joinPoint){
        try{
            return joinPoint.proceed();
        }catch (Throwable e){
            ExceptionInfo info=new ExceptionInfo();
            //异常类记录
            info.setClassName(joinPoint.getTarget().getClass().getName());
            info.setMethodName(joinPoint.getSignature().getName());
            info.setLogTime(new Date());
            info.setMessage(e.toString());
            //上传日志系统,自行完善
            //ExceptionReportUtils.report(info);
            return null;
        }
    }
}
