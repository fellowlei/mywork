package com.mark.frame.util.aop.test;

import com.mark.frame.util.aop.AopInvocation;

/**
 * Created by lulei on 2017/3/10.
 */
public class TestDaoImpl extends AbstractDao implements TestDao {

    @Override
    public void insert() {
        System.out.println("TestDaoImpl.insert");
    }

    public static void main(String[] args) {
        TestDao dao = new TestDaoImpl();
        dao = AopInvocation.getProxy(dao);
        dao.insert();
    }

}
