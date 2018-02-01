package com.mark.frame.db2;

/**
 * Created by lulei on 2016/3/4.
 */
public class DBRouteImpl implements DBRoute {
    private int dCount = 3;
    private int tCount = 2;

    @Override
    public int getMid(int id) {
        return id % (dCount * tCount);
    }

    @Override
    public int getTIndex(int id) {
        return getMid(id) % tCount;
    }

    @Override
    public int getDIndex(int id) {
        return getMid(id) % dCount;
    }

    public static void main(String[] args) {
        DBRouteImpl dbRoute = new DBRouteImpl();
        for(int i=0; i<10; i++){
            System.out.println(dbRoute.getDIndex(i) + ":" + dbRoute.getTIndex(i));
        }

    }
}
