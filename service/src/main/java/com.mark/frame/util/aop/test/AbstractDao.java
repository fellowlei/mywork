package com.mark.frame.util.aop.test;

import com.mark.frame.util.aop.AopMethod;
import com.mark.frame.util.conn.ThreadConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by lulei on 2017/3/10.
 */
public class AbstractDao implements AopMethod {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void before(Object obj) {
//        connection = ThreadConnectionUtil.getConnection();
//        try {
//            connection.setAutoCommit(false);
//        } catch (SQLException e) {
//            new RuntimeException(e.getMessage());
//        }
        System.out.println("AbstractDao.before");
    }

    @Override
    public void after(Object obj) {
//        try {
//            connection.commit();
//        } catch (SQLException e) {
//            throw new RuntimeException(e.getMessage());
//        }
        System.out.println("AbstractDao.after");
    }
}
