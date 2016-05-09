package com.mark.frame.db;

import java.sql.Connection;

/**
 * Created by lulei on 2016/1/25.
 */
public interface DBRoute {

    public Integer getConnection(Integer num);

    public Integer getTableName(Integer num);

    public Integer getTableCount();

    public Integer getDBCount();

    public Integer getTotalTableCount();

}
