package com.mark.frame.aop.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Date;

/**
 * Created by lulei on 2018/1/24.
 */
@Aspect
public class TimeAspect {
    /**
     * �����е㺯��������controller���µ�������Controller��β�������з���
     */
    @Pointcut("execution(* com.mark.frame.aop.UserDaoImpl.*(..))")
    void timer() {
    }

    @Around("timer()")
    public Object logTimer(ProceedingJoinPoint joinPoint) throws  Throwable{
        MonitorTime monitorTime = new MonitorTime();
        String clazzName = joinPoint.getTarget().getClass().getName();//��ȡĿ��������
        String methodName = joinPoint.getSignature().getName(); //��ȡĿ���෽������

        monitorTime.setClassName(clazzName);//��¼������
        monitorTime.setMethodName(methodName);//��¼��Ӧ��������
        monitorTime.setLogTime(new Date());//��¼ʱ��

        // ��ʱ������Ŀ�꺯��
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long time = System.currentTimeMillis() - start;

        //��������ʱ��
        monitorTime.setConsumeTime(time);
        //��monitorTime��¼����Ϣ�ϴ������ϵͳ����û��ʵ�֣���Ҫ����ʵ�ּ���
        //MonitorUtil.report(monitorTime)
        System.out.println(monitorTime);
        return result;

    }
}
