package com.mark.frame.util;

import com.mark.frame.auto.test.Hello;
import com.mark.frame.test.DefaultWorker;
import com.mark.frame.test.Worker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lulei on 2017/3/10.
 */
public class AopUtil implements InvocationHandler {
    private Object obj;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        method.invoke(obj,args);
        System.out.println("after");
        return null;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public static <T> T getProxy(T obj){
        AopUtil aopUtil =  new AopUtil();
        aopUtil.setObj(obj);
        return (T) Proxy.newProxyInstance(AopUtil.class.getClassLoader(),obj.getClass().getInterfaces(),aopUtil);
    }

    public static void test(){
        Worker worker = AopUtil.getProxy(new DefaultWorker());
        worker.start();
    }

    public static void main(String[] args) {
        test();
    }
}
