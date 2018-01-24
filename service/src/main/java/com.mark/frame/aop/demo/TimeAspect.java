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
     * 定义切点函数，过滤controller包下的名称以Controller结尾的类所有方法
     */
    @Pointcut("execution(* com.mark.frame.aop.UserDaoImpl.*(..))")
    void timer() {
    }

    @Around("timer()")
    public Object logTimer(ProceedingJoinPoint joinPoint) throws  Throwable{
        MonitorTime monitorTime = new MonitorTime();
        String clazzName = joinPoint.getTarget().getClass().getName();//获取目标类名称
        String methodName = joinPoint.getSignature().getName(); //获取目标类方法名称

        monitorTime.setClassName(clazzName);//记录类名称
        monitorTime.setMethodName(methodName);//记录对应方法名称
        monitorTime.setLogTime(new Date());//记录时间

        // 计时并调用目标函数
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long time = System.currentTimeMillis() - start;

        //设置消耗时间
        monitorTime.setConsumeTime(time);
        //把monitorTime记录的信息上传给监控系统，并没有实现，需要自行实现即可
        //MonitorUtil.report(monitorTime)
        System.out.println(monitorTime);
        return result;

    }
}
