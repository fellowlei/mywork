package com.mark.frame.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lulei on 2018/1/24.
 */
public class AopTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop/aop.xml");
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop/aopxml.xml");
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop/aopdemo.xml");
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        userDao.addUser();
    }
}
