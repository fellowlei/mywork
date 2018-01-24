package com.mark.frame.aop.demo;

import java.util.Date;

/**
 * Created by lulei on 2018/1/24.
 */
public class MonitorTime {
    private String className;
    private String methodName;
    private Date logTime;
    private long consumeTime;

    public long getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(long consumeTime) {
        this.consumeTime = consumeTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    @Override
    public String toString() {
        return "MonitorTime{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", logTime=" + logTime +
                ", consumeTime=" + consumeTime +
                '}';
    }
}
