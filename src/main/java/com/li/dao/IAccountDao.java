package com.li.dao;

import com.li.domain.Account;

import java.util.List;

/**'
 * 账户的持久层方法*/
public interface IAccountDao {

    /**
     * 查询所有*/
    List<Account> findAllAccount();

    //查询一个账户

    Account findAccountById(int accountId);

    /**
     * 保存操作*/
    void saveAccount(Account account);

    //更新操作
    void updateAccount(Account account);

    //删除账户
    void daleteAccountById(int accountId);

    /**
     * 根据名称查询账户
     * @param accountName
     * @return 如果正确返回true
     */
    Account findAccountByName(String accountName);
}
