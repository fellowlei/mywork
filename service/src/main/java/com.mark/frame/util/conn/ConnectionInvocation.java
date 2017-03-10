package com.mark.frame.util.conn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by lulei on 2017/3/10.
 */
public class ConnectionInvocation implements InvocationHandler {
    private Object object;

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return invokeConnection(method, args);
    }

    private Object invokeConnection(Method method, Object[] args) throws SQLException {
        Connection connection = ThreadConnectionUtil.getConnection();
        boolean origin =  connection.getAutoCommit();
        try {
            connection.setAutoCommit(false);
            Object result = method.invoke(object, args);
            connection.commit();
            return result;
        }catch (Exception e){
            connection.rollback();
            throw new RuntimeException(e.getMessage());
        }finally {
            connection.setAutoCommit(origin);
        }
    }


}
