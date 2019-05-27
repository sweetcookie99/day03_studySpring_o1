package test;

import com.li.domain.Account;
import com.li.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Author: LTWO
 * @Date: 2019/5/24 19:35
 * @Version 1.0
 * 使用Junit单元测试：测试我们的配置
 */
public class AccountServiceTest {
    @Test
    public void testFindAll() {
        //获取容器
        ApplicationContext ac= new ClassPathXmlApplicationContext("bean.xml");
        IAccountService accountService = ac.getBean("accountService", IAccountService.class);
        List<Account> allAccount = accountService.findAllAccount();
        for (Account account:allAccount){
            System.out.println(account);
        }
    }

    @Test
    public void testTransfer() {
        ApplicationContext ac= new ClassPathXmlApplicationContext("bean.xml");
        IAccountService accountService = ac.getBean("accountService", IAccountService.class);
        accountService.transger("aaa","cccc", 800f);

    }
}
