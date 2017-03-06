package com.mark.frame.auto;

import com.mark.frame.auto.test.Hello;
import com.mark.frame.auto.test.OneHello;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lulei on 2017/3/6.
 */
public class DefaultInvocationHandler implements InvocationHandler {
    private Object object;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println("before");
        Object result = method.invoke(object,args);
//        System.out.println("end");
        return result;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * gen proxy
     */
    public static <T> T getProxy(T obj){
        DefaultInvocationHandler handler = new DefaultInvocationHandler();
        handler.setObject(obj);
        return (T) Proxy.newProxyInstance(DefaultInvocationHandler.class.getClassLoader(),obj.getClass().getInterfaces(),handler);
    }

    public static void main(String[] args) {
        Hello h1 = new OneHello();
        Hello proxy =DefaultInvocationHandler.getProxy(h1);
        proxy.sayHello();
    }
}
