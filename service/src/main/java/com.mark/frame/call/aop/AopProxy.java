package com.mark.frame.call.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lulei on 2018/3/8.
 */
public class AopProxy {
    public static <T> T proxy(final T obj) {
        return (T) Proxy.newProxyInstance(AopProxy.class.getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before");
                Object result = method.invoke(obj, args);
                System.out.println("after");
                return result;
            }
        });
    }

    public static <T> T proxy2(final T obj) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before 2");
                Object result =  method.invoke(obj,objects);
                System.out.println("after 2");
                return result;
            }
        });
        return (T) enhancer.create();
    }

    public static void main(String[] args) {
        Animal dog = new Dog();
        Animal dog2 =  proxy2(dog);
        dog2.say();
    }
}
