package com.mark.frame.db3;

import com.oracle.webservices.internal.api.message.BasePropertySet;

import javax.sql.DataSource;

/**
 * Created by lulei on 2017/7/7.
 */
public class DataSourceRouter {
    public static ThreadLocal<RouterStrategy> currentDataSourceHolder = new ThreadLocal<RouterStrategy>();

    public static void setRouteStrategy(RouterStrategy routeStrategy) {
       currentDataSourceHolder.set(routeStrategy);
    }
}
