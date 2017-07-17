package com.mark.frame.db3;


import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by lulei on 2017/7/7.
 */
public class MultiDataSource implements DataSource {
    static Logger logger = Logger.getLogger(MultiDataSource.class.getCanonicalName());
    //当前线程对应的实际DataSource
    private ThreadLocal<DataSource> currentDataSourceHolder = new ThreadLocal<DataSource>();
    //使用Key-Value映射的DataSource
    private Map<String , DataSource> mappedDataSources;
    //使用横向切分的分布式DataSource
    private ArrayList<DataSource> clusterDataSources;


    public MultiDataSource(Map<String, DataSource> mappedDataSources, ArrayList<DataSource> clusterDataSources) {
        this.mappedDataSources = mappedDataSources;
        this.clusterDataSources = clusterDataSources;
    }

    /**
     * 数据库连接池初始化
     * 该方法通常在web 应用启动时调用
     */
    public void initialMultiDataSource(){
        for(DataSource ds : clusterDataSources){
            if(ds != null){
                Connection conn = null;
                try {
                    conn = ds.getConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally{
                    if(conn != null){
                        try {
                            conn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        conn = null;
                    }
                }
            }
        }
        Collection<DataSource> dsCollection = mappedDataSources.values();
        for(DataSource ds : dsCollection){
            if(ds != null){
                Connection conn = null;
                try {
                    conn = ds.getConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally{
                    if(conn != null){
                        try {
                            conn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        conn = null;
                    }
                }
            }
        }
    }

    /**
     * 获取当前线程绑定的DataSource
     * @return
     */
    public DataSource getCurrentDataSource() {
        //如果路由策略存在，且更新过，则根据路由算法选择新的DataSource
        RouterStrategy strategy = DataSourceRouter.currentDataSourceHolder.get();
        if(strategy == null){
            throw new IllegalArgumentException("DataSource RouterStrategy No found.");
        }
        if(strategy != null && strategy.isRefresh()){
            if(RouterStrategy.RouteStrategy.MAP.equals(strategy.getType())){
                this.choiceMappedDataSources(strategy.getKey());

            }else if(RouterStrategy.RouteStrategy.CLUSTER.equals(strategy.getType())){
                this.routeClusterDataSources(strategy.getRouteFactor());
            }
            strategy.setRefresh(false);
        }
        return currentDataSourceHolder.get();
    }

    /**
     * 使用Key选择当前的数据源
     * @param key
     */
    public void choiceMappedDataSources(String key){
        DataSource ds = this.mappedDataSources.get(key);
        if(ds == null){
            throw new IllegalStateException("No Mapped DataSources Exist!");
        }
        this.currentDataSourceHolder.set(ds);
    }


    /**
     * 使用取模算法，在群集数据源中做路由选择
     * @param routeFactor
     */
    public void routeClusterDataSources(int routeFactor){
        int size = this.clusterDataSources.size();
        if(size == 0){
            throw new IllegalStateException("No Cluster DataSources Exist!");
        }
        int choosen = routeFactor % size;
        DataSource ds = this.clusterDataSources.get(choosen);
        if(ds == null){
            throw new IllegalStateException("Choosen DataSources is null!");
        }
        logger.info("Choosen DataSource No." + choosen + " : " + ds.toString());
        this.currentDataSourceHolder.set(ds);
    }



    public Map<String, DataSource> getMappedDataSources() {
        return mappedDataSources;
    }

    public void setMappedDataSources(Map<String, DataSource> mappedDataSources) {
        this.mappedDataSources = mappedDataSources;
    }

    public ArrayList<DataSource> getClusterDataSources() {
        return clusterDataSources;
    }

    public void setClusterDataSources(ArrayList<DataSource> clusterDataSources) {
        this.clusterDataSources = clusterDataSources;
    }

    @Override
    public Connection getConnection() throws SQLException {
        if(getCurrentDataSource() != null){
            return getCurrentDataSource().getConnection();
        }
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        if(getCurrentDataSource() != null){
            return getCurrentDataSource().getConnection(username , password);
        }
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        if(getCurrentDataSource() != null){
            return getCurrentDataSource().getLogWriter();
        }
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        if(getCurrentDataSource() != null){
            getCurrentDataSource().setLogWriter(out);
        }
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        if(getCurrentDataSource() != null){
            getCurrentDataSource().setLoginTimeout(seconds);
        }
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        if(getCurrentDataSource() != null){
            return getCurrentDataSource().getLoginTimeout();
        }
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        if(getCurrentDataSource() != null){
            return getCurrentDataSource().getParentLogger();
        }
        return null;
    }

    /* (non-Javadoc)
     * 该接口方法since 1.6
     * 不是所有的DataSource都实现有这个方法
     * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
     */
    public boolean isWrapperFor(Class<?> iface) throws SQLException {

//      if(getCurrentDataSource() != null){
//          return getCurrentDataSource().isWrapperFor(iface);
//      }
        return false;
    }

    /* (non-Javadoc)
     * 该接口方法since 1.6
     * 不是所有的DataSource都实现有这个方法
     * @see java.sql.Wrapper#unwrap(java.lang.Class)
     */
    public <T> T unwrap(Class<T> iface) throws SQLException {
//      if(getCurrentDataSource() != null){
//          return getCurrentDataSource().unwrap(iface);
//      }
        return null;
    }
}
