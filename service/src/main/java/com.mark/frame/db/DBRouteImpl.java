package com.mark.frame.db;


/**
 * Created by lulei on 2016/3/3.
 */
public abstract class DBRouteImpl implements DBRoute {

    @Override
    public Integer getConnection(Integer id) {
        checkId(id);
        Integer pos = (id - 1) / getTableCount();
        pos = pos % getDBCount();
        return pos;
    }


    @Override
    public Integer getTableName(Integer id) {
        checkId(id);
        Integer totalCount = getTotalTableCount();
        Integer pos = id % totalCount;
        if (pos == 0) {
            pos = totalCount;
        }
        return pos;
    }

    @Override
    public Integer getTotalTableCount() {
        return getTableCount() * getDBCount();
    }


    public void test(Integer id) {
        checkId(id);
        System.out.print("id: " + id + ",db: " + getConnection(id) + ",table:" + getTableName(id));
        System.out.println();
    }

    private void checkId(Integer id) {
        if (id < 1) {
            throw new RuntimeException("id must > 0! input params: id=" + id);
        }
    }

    public static void main(String[] args) {

    }
}
