package com.mark.frame.util.aop;

import com.mark.frame.test.AopWorker;
import com.mark.frame.test.DefaultAopWorker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lulei on 2017/3/10.
 */
public class AopInvocation implements InvocationHandler {
    private Object object;
    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 无侵入动态代理 before
        if(object instanceof  AopMethod){
            ((AopMethod) object).before(object);
        }
        Object result = method.invoke(object, args);
        // 无侵入动态代理 before
        if(object instanceof  AopMethod){
            ((AopMethod) object).after(object);
        }
        return result;
    }

    public static <T> T getProxy(T obj){
        AopInvocation aopInvocation =new AopInvocation();
        aopInvocation.setObject(obj);
        return (T) Proxy.newProxyInstance(AopInvocation.class.getClassLoader(), obj.getClass().getInterfaces(), aopInvocation);
    }

    public static void main(String[] args) {
        DefaultAopWorker worker = new DefaultAopWorker();
        AopWorker proxy = getProxy(worker);
        proxy.start();
    }
}
