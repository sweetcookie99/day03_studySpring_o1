package com.li.service;

import com.li.domain.Account;

import java.util.List;

/**
 * @Author: LTWO
 * @Date: 2019/5/24 18:02
 * @Version 1.0
 * 账户的业务层方法
 */
public interface IAccountService {
    /**
     * 查询所有
     */
    List<Account> findAllAccount();

    //查询一个账户

    Account findAccountById(int accountId);

    /**
     * 保存操作
     */
    void saveAccount(Account account);

    //更新操作
    void updateAccount(Account account);

    //删除账户
    void daleteAccountById(int accountId);

    /**
     * 转账
     * @param sourceName  转出账户名称
     * @param targetName  转入账户名称
     * @param money  转账金额
     */
    void transger(String sourceName, String targetName,Float money);

}