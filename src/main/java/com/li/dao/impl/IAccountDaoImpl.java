package com.li.dao.impl;

import com.li.dao.IAccountDao;
import com.li.domain.Account;
import com.li.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: LTWO
 * @Date: 2019/5/24 19:04
 * @Version 1.0
 * 持久层实现类
 */
@Repository("accountDao")
public class IAccountDaoImpl implements IAccountDao {

    @Autowired
    private QueryRunner runner;
    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }


    public List<Account> findAllAccount() {
        try {
          return   runner.query(connectionUtils.getThreadLocalConnection(),"select * from account",new BeanListHandler<Account>(Account.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Account findAccountById(int accountId) {
        try {
            return   runner.query(connectionUtils.getThreadLocalConnection(),"select * from account where id=?",new BeanHandler<Account>(Account.class),accountId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAccount(Account account) {
        try{
            runner.update(connectionUtils.getThreadLocalConnection(),"insert into account(name,money)value(?,?)",account.getName(),account.getMoney());
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public void updateAccount(Account account) {
        try{
            runner.update(connectionUtils.getThreadLocalConnection(),"update account set name=?,money=? where id=?",account.getName(),account.getMoney(),account.getId());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void daleteAccountById(int accountId) {
        try{
            runner.update(connectionUtils.getThreadLocalConnection(),"delete from account where id=?",accountId);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Account findAccountByName(String accountName) {
        try {
            List<Account> acocunt= runner.query(connectionUtils.getThreadLocalConnection(),"select * from account where name=?",new BeanListHandler<Account>(Account.class),accountName);
            if (acocunt==null || acocunt.size()==0){
                return  null;
            }
            if (acocunt.size() >1){
                throw new RuntimeException("结果不唯一，结果集有问题");
            }
            return acocunt.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
