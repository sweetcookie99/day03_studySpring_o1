package com.li.utils;

import java.sql.SQLException;

/**
 * @Author: LTWO
 * @Date: 2019/5/26 16:16
 * @Version 1.0
 * 和事务管理相关的工具类，它包含了，开启事务，提交事务和回滚事务
 */
public class TransactionManager {
    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {

        this.connectionUtils = connectionUtils;
    }

    /**
     * 开启事务*/
    public void beginTransaction(){
        try {
            connectionUtils.getThreadLocalConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 提交事务*/
    public void conmmit(){
        try {
            connectionUtils.getThreadLocalConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 回滚事务*/
    public void rollback(){
        try {
            connectionUtils.getThreadLocalConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 释放连接
     */
    public void release(){
        try {
            connectionUtils.getThreadLocalConnection().close();//还回连接池中
            connectionUtils.removeConnection();//解绑
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
