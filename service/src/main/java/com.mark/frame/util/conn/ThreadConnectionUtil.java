package com.mark.frame.util.conn;

import java.sql.Connection;

/**
 * Created by lulei on 2017/3/10.
 */
public class ThreadConnectionUtil {
    public static  ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
    public static Connection getConnection(){
        return threadLocal.get();
    }

    public static void setConnection(Connection conn){
        threadLocal.set(conn);
    }


}
