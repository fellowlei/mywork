package com.mark.frame.auto;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lulei on 2017/3/2.
 */
public class AutoSwitchInvocation implements InvocationHandler {
    private static final Log log = LogFactory.getLog(AutoSwitchInvocation.class);
    private Object master;
    private Object slave;
    private static Map<String,AutoSwitch> hashMap = new ConcurrentHashMap<String, AutoSwitch>();
    private static AtomicInteger count =new AtomicInteger(0);

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(count.incrementAndGet());
        // ��ȡ��ʱ��,ÿ���ӿ�����һ����ʱ��
        AutoSwitch autoSwitch = null;
        if(hashMap.containsKey(method.getName())){
           autoSwitch = hashMap.get(method.getName());
        }else{
            hashMap.put(method.getName(),new AutoSwitch());
            autoSwitch = hashMap.get(method.getName());
        }


        Object result = null;
        if(autoSwitch.fail.get() >= 3){ // ʧ��3�ε��ñ�
            if(autoSwitch.failTimeout.get() < System.currentTimeMillis()){ // ����10�룬�Զ�������
                autoSwitch.fail.set(0);
            }
            System.out.println("call slave");
            result = invokeSlaveInstance(method, args);
        } else {
            System.out.println("call master");
            result = invokeMasterInstance(method, args,autoSwitch);
        }
//        debug(autoSwitch);
        return result;
    }

    private Object invokeMasterInstance(Method method, Object[] args,AutoSwitch autoSwitch) {
        if(autoSwitch.spendTime.get() < System.currentTimeMillis()){ // 10���ڵ���3��
            System.out.println("set spend time");
            autoSwitch.spendTime.set(System.currentTimeMillis() + 10 * 1000);
            autoSwitch.fail.set(0);
        }
        Object result;
        try {
            result = method.invoke(master, args);
        } catch (Exception e) {
            log.error(e.getMessage());
//            e.printStackTrace();
            autoSwitch.fail.incrementAndGet();
            if(autoSwitch.fail.get() >= 3){ // ʧ��3�ε��ñ�
                autoSwitch.failTimeout.set(System.currentTimeMillis() + 10 * 1000); // 10��
            }
            result = invokeSlaveInstance(method, args);
        }
        return result;
    }

    private Object invokeSlaveInstance(Method method, Object[] args) {
        Object result;
        try {
            result = method.invoke(slave, args);
        } catch (Exception e2) {
            log.error(e2.getMessage());
//            e2.printStackTrace();
            throw new RuntimeException(e2.getMessage());
        }
        return result;
    }

    public void setMaster(Object master) {
        this.master = master;
    }

    public void setSlave(Object slave) {
        this.slave = slave;
    }

    public void debug(AutoSwitch autoSwitch){
        System.out.println("fail:" + autoSwitch.fail + ",failTimeout:" + autoSwitch.failTimeout + ",spendTime:" + autoSwitch.spendTime  + ",time:" + System.currentTimeMillis() + ",failtimeout:" + (autoSwitch.failTimeout.get() > System.currentTimeMillis())+",spendTime:" + (autoSwitch.spendTime.get() > System.currentTimeMillis()));
    }

    public static <T> T genAutoSwitch(T master,T slave){
        AutoSwitchInvocation invocation = new AutoSwitchInvocation();
        invocation.setMaster(master);
        invocation.setSlave(slave);
        return (T) Proxy.newProxyInstance(AutoSwitchInvocation.class.getClassLoader(),master.getClass().getInterfaces(),invocation);
    }
}
