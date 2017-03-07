package com.mark.frame.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lulei on 2017/3/6.
 */
public class AbstractDataSourceDBRoute extends DBRouteImpl
{
    private List<DataSource> list =new ArrayList<DataSource>();

    public Connection getConnectionInner(Integer num) {
        Integer pos = getConnection(num);
        try {
            return list.get(pos).getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
