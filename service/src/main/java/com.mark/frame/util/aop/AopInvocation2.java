package com.mark.frame.util.aop;

import com.mark.frame.test.AopWorker;
import com.mark.frame.test.DefaultAopWorker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lulei on 2017/3/10.
 */
public class AopInvocation2 implements InvocationHandler {
    private Object object;
    private AopMethod aopMethod;
    public void setObject(Object object) {
        this.object = object;
    }

    public void setAopMethod(AopMethod aopMethod) {
        this.aopMethod = aopMethod;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(aopMethod != null){
            aopMethod.before(object);
        }
        Object result = method.invoke(object, args);
        if(aopMethod != null){
            aopMethod.after(object);
        }
        return result;
    }


    public static void main(String[] args) {
        DefaultAopWorker worker = new DefaultAopWorker();
        AopInvocation2 aopInvocation =new AopInvocation2();
        aopInvocation.setObject(worker);
        aopInvocation.setAopMethod(worker);
        AopWorker proxy =(AopWorker) Proxy.newProxyInstance(AopInvocation2.class.getClassLoader(),worker.getClass().getInterfaces(), aopInvocation);
        proxy.start();
    }
}
