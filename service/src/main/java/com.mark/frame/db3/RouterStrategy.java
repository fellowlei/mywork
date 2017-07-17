package com.mark.frame.db3;

/**
 * Created by lulei on 2017/7/7.
 */
public class RouterStrategy {
    private boolean refresh;
    private RouteStrategy type;
    private String key;
    private int routeFactor;

    public boolean isRefresh() {
        return refresh;
    }

    public Object getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public int getRouteFactor() {
        return routeFactor;
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }


    enum RouteStrategy{
        MAP,CLUSTER
    }
}
