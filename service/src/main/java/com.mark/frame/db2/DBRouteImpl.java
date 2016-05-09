package com.mark.frame.db2;

/**
 * Created by lulei on 2016/3/4.
 */
public class DBRouteImpl implements DBRoute {
    @Override
    public int getTableCount() {
        return 5;
    }

    @Override
    public int getDBCount() {
        return 6;
    }

    @Override
    public int getTotalTableCount() {
        return getDBCount() * getTableCount();
    }

    @Override
    public int getConnectionIndex(int id) {
        int pos = (id - 1) / getTableCount();
        pos = pos % getDBCount();
        return pos;
    }

    @Override
    public int getTableIndex(int id) {
        int pos = id % getTotalTableCount();
        if (pos == 0) {
            pos = getTotalTableCount();
        }
        return pos;
    }

    public void testRoute(int id) {
        System.out.println("id:" + id + ",db:" + getConnectionIndex(id) + ",table: " + getTableIndex(id));
    }

    public static void main(String[] args) {
        DBRouteImpl route = new DBRouteImpl();
        for (int i = 1; i <= 40; i++) {
            route.testRoute(i);
        }
    }
}
