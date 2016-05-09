package com.mark.frame.db;

import java.sql.Connection;
import java.util.List;

/**
 * Created by lulei on 2016/3/3.
 */
public abstract class AbstractDBRoute extends DBRouteImpl {
    List<Connection> connectionList = null;

    public void setConnectionList(List<Connection> connectionList) {
        this.connectionList = connectionList;
    }

    public Connection getConnectionInner(Integer num) {
        Integer pos = getConnection(num);
        return connectionList.get(pos);
    }

}
