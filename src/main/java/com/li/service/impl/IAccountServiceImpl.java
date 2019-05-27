package com.li.service.impl;

import com.li.dao.IAccountDao;
import com.li.domain.Account;
import com.li.service.IAccountService;
import com.li.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LTWO
 * @Date: 2019/5/24 18:13
 * @Version 1.0
 * 事务的控制都要在业务层的
 */
@Service("accountService")
public class IAccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountDao accountDao;
    private TransactionManager manager;

    public void setManager(TransactionManager manager) {
        this.manager = manager;
    }

    public void setAccountDao(IAccountDao accountDao) {

        this.accountDao = accountDao;
    }

    public List<Account> findAllAccount() {
        try{
            //1.开启事务
            manager.beginTransaction();
            //2.执行操作
            List<Account> accounts = accountDao.findAllAccount();
            //3.提交事务
            manager.conmmit();
            //4.返回结果
            return accounts;
        }catch (Exception e){
            //5.回滚操作
            manager.rollback();
            throw new RuntimeException(e);
        }finally {
            //6.释放连接
            manager.release();
        }

    }

    public Account findAccountById(int accountId) {
        try{
            //1.开启事务
            manager.beginTransaction();
            //2.执行操作
            Account accounts = accountDao.findAccountById(accountId);
            //3.提交事务
            manager.conmmit();
            //4.返回结果
            return accounts;
        }catch (Exception e){
            //5.回滚操作
            manager.rollback();
            throw new RuntimeException();
        }finally {
            //6.释放连接
            manager.release();
        }

    }

    public void saveAccount(Account account) {
        try{
            //1.开启事务
            manager.beginTransaction();
            //2.执行操作
            accountDao.saveAccount(account);
            //3.提交事务
            manager.conmmit();
            //4.返回结果
        }catch (Exception e){
            //5.回滚操作
            manager.rollback();
        }finally {
            //6.释放连接
            manager.release();
        }
        accountDao.saveAccount(account);
    }

    public void updateAccount(Account account) {
        try{
            //1.开启事务
            manager.beginTransaction();
            //2.执行操作
            accountDao.updateAccount(account);
            //3.提交事务
            manager.conmmit();
            //4.返回结果

        }catch (Exception e){
            //5.回滚操作
            manager.rollback();
        }finally {
            //6.释放连接
            manager.release();
        }
        accountDao.updateAccount(account);
    }

    public void daleteAccountById(int accountId) {
        try{
            //1.开启事务
            manager.beginTransaction();
            //2.执行操作
            accountDao.daleteAccountById(accountId);
            //3.提交事务
            manager.conmmit();
            //4.返回结果
        }catch (Exception e){
            //5.回滚操作
            manager.rollback();
        }finally {
            //6.释放连接
            manager.release();
        }
        accountDao.daleteAccountById(accountId);
    }

    public void transger(String sourceName, String targetName, Float money) {

        //1.根据名称查询转出账户
        Account source = accountDao.findAccountByName(sourceName);
        //2.根据名查询传入账户
        Account target = accountDao.findAccountByName(targetName);
        //3.转出账户减钱

        source.setMoney(source.getMoney()-money);

        //3.转入账户加钱
        target.setMoney(target.getMoney()+money);
        //4.分别更新转入和转出账户
        accountDao.updateAccount(source);
        accountDao.updateAccount(target);
        if (source.getMoney()-money<=0){
            try {
                throw new Exception("账户余额不足");
            } catch (Exception e) {
                throw new RuntimeException("账户余额不足");
            }
        }
    }
}
