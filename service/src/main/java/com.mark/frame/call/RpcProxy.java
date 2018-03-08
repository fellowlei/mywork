package com.mark.frame.call;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class RpcProxy {
    public static <T> T proxy(final T obj, final String name) {
        return (T) Proxy.newProxyInstance(RpcProxy.class.getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before");
                Object result = HttpProxy.invokeHttpProxy(name,method,args);
                System.out.println("after");
                return result;
            }
        });
    }

    public static <T> T proxy2(final T obj, final String name) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("before 2");
                Object result = HttpProxy.invokeHttpProxy(name,method,args);
                System.out.println("after 2");
                return result;
            }
        });
        return (T) enhancer.create();
    }

    public static void main(String[] args) {

    }
}
