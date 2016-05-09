package com.mark.frame.db2;

/**
 * Created by lulei on 2016/3/4.
 */
public interface DBRoute {
    public int getTableCount();

    public int getDBCount();

    public int getTotalTableCount();

    public int getConnectionIndex(int id);

    public int getTableIndex(int id);
}
