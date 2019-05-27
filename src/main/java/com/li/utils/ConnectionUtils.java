package com.li.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author: LTWO
 * @Date: 2019/5/26 16:04
 * @Version 1.0
 * 连接的工具类，它用于从数据源中获取一个连接，并且实现和线程的绑定
 */
public class ConnectionUtils {
    private ThreadLocal<Connection> t1 = new ThreadLocal<Connection>();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getThreadLocalConnection(){
        //1,先从ThreadLocal上获取
        Connection conn = t1.get();
        //2.判断当前线程上是否有连接
        if (conn==null){
            //3.从数据源中获取一个连接，并且存到ThreadLocal中去
            try {
                conn = dataSource.getConnection();
                t1.set(conn);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        //返回当前线程上的连接
        return conn;
    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection(){
        t1.remove();
    }
}
