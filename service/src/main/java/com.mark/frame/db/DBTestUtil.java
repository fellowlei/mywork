package com.mark.frame.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by lulei on 2016/1/25.
 */
public class DBTestUtil {
    public List<Connection> connections = new ArrayList<Connection>();

    public List<Connection> getConnections() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }
        try {
            connections.add(DriverManager.getConnection("jdbc:mysql://192.168.10.1:3306/test", "root", "123"));
            connections.add(DriverManager.getConnection("jdbc:mysql://192.168.10.2:3306/test", "root", "123"));
            connections.add(DriverManager.getConnection("jdbc:mysql://192.168.10.3:3306/test", "root", "123"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connections;
    }


}
