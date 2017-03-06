package com.mark.frame.db3;

import com.jolbox.bonecp.BoneCPDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lulei on 2017/3/6.
 */
public class DBPool {
    public static void main(String[] args) throws SQLException {
       DataSource ds =  genDBCPPool();
        testDB(ds);
    }

    public static  void testDB(DataSource ds) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement pstmt =conn.prepareStatement("select count(*) from user");
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            Integer count = rs.getInt(1);
            System.out.println(count);
        }
        conn.close();
    }




    public static  List<DataSource> genDBCPPoolList(){
        List<DataSource> dataSourceList = new ArrayList<DataSource>();
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://192.168.81.135:3306/test1");
        ds.setUsername("root");
        ds.setPassword("1");

        dataSourceList.add(ds);

        BasicDataSource ds2 = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://192.168.81.135:3306/test2");
        ds.setUsername("root");
        ds.setPassword("1");

        dataSourceList.add(ds);
        dataSourceList.add(ds2);

        return dataSourceList;
    }

    public static DataSource genDBCPPool(){
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://192.168.81.135:3306/test");
        ds.setUsername("root");
        ds.setPassword("1");
        return ds;
    }

    public static DataSource genBoneCPPool(){
        BoneCPDataSource ds = new BoneCPDataSource();
        ds.setDriverClass("com.mysql.jdbc.Driver");
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        ds.setUsername("root");
        ds.setPassword("1");
        return ds;
    }

    public DataSource genC3p0Pool() throws PropertyVetoException {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setDriverClass("com.mysql.jdbc.Driver");
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/editortest");
        ds.setUser("root");
        ds.setPassword("123456");
        return ds;
    }

}

