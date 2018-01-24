package com.mark.frame.aop.demo2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Date;

/**
 * Created by lulei on 2018/1/24.
 * AOPʵ�� ���棬Ȩ����֤�����ݴ����������
 */
@Aspect
public class ExceptionMonitor {
    /**
     * �����쳣�����
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
            //�쳣���¼
            info.setClassName(joinPoint.getTarget().getClass().getName());
            info.setMethodName(joinPoint.getSignature().getName());
            info.setLogTime(new Date());
            info.setMessage(e.toString());
            //�ϴ���־ϵͳ,��������
            //ExceptionReportUtils.report(info);
            return null;
        }
    }
}
