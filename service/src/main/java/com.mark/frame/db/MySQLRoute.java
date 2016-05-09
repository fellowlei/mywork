package com.mark.frame.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lulei on 2016/3/3.
 */
public class MySQLRoute extends AbstractDBRoute implements DBExecutor {
    @Override
    public Integer getTableCount() {
        return 2;
    }

    @Override
    public Integer getDBCount() {
        return 3;
    }


    @Override
    public int executeUpdate(String sql, List<Object> params, String baseName, Integer id) {
        Integer dbPos = getConnection(id);
        String tbName = baseName + getTableName(id);
        Connection conn = connectionList.get(dbPos);
        sql = sql.replaceFirst("#table#", tbName);
//        System.out.println(sql);
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            if (params != null && params.size() > 0) {
                for (int i = 0; i < params.size(); i++) {
                    pstmt.setObject(i + 1, params.get(i));
                }
            }
            int row = pstmt.executeUpdate();
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Map<String, Object>> executeQuery(String sql, List<Object> params, String baseName, Integer id) {
        Connection conn = getConnectionInner(id);
        String tbName = baseName + getTableName(id);
        sql = sql.replaceFirst("#table#", tbName);
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            if (params != null && params.size() > 0) {
                for (int i = 0; i < params.size(); i++) {
                    pstmt.setObject(i + 1, params.get(i));
                }
            }
            ResultSet rs = pstmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int row = rsmd.getColumnCount();

            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = null;
            while (rs.next()) {
                for (int i = 0; i < row; i++) {
                    map = new HashMap<String, Object>();
                    Object val = rs.getObject(i + 1);
                    String name = rsmd.getColumnName(i + 1);
                    map.put(name, val);
                    resultList.add(map);
                }
            }
            return resultList;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int execute(String sql, List<Object> params, String baseName, Integer id) {
        return this.executeUpdate(sql, params, baseName, id);
    }


    public static void test() {
        MySQLRoute route = new MySQLRoute();
        route.setConnectionList(new DBTestUtil().getConnections());

        System.out.println("start delete...");
        // test delete
        for (int i = 1; i <= 6; i++) {
            int row = route.execute("delete from #table#", null, "test_", i);
            System.out.println(row);
        }

        System.out.println("start insert...");
        // test insert
        for (int i = 1; i <= 100; i++) {
            List<Object> params = new ArrayList<Object>();
            params.add(i);
            int row = route.execute("insert into #table# values(?)", params, "test_", i);
        }

        System.out.println("start query...");
        // test query
        for (int i = 1; i <= 6; i++) {
            List<Map<String, Object>> queryResult = route.executeQuery("select * from #table#", null, "test_", i);
            System.out.println(queryResult);
        }
    }

    public static void main(String[] args) {
        test();
    }
}
