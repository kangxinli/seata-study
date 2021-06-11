package com.sample.tcc.local.action.starter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

import com.sample.tcc.local.ApplicationKeeper;
import com.sample.tcc.local.action.ResultHolder;
import com.sample.tcc.local.action.impl.TccActionOneImpl;
import com.sample.tcc.local.action.impl.TccActionTwoImpl;
import com.sample.tcc.local.action.service.TccTransactionService;

import io.seata.common.util.StringUtils;

/**
 * The type Local tcc transaction starter.
 *
 * @author zhangsen
 */
public class LocalTccTransactionStarter {

    /**
     * The Application context.
     */
    static AbstractApplicationContext applicationContext = null;

    /**
     * The Tcc transaction service.
     */
    static TccTransactionService tccTransactionService = null;

    /**
     * The Tcc action one.
     */
    static TccActionOneImpl tccActionOne = null;

    /**
     * The Tcc action two.
     */
    static TccActionTwoImpl tccActionTwo = null;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws InterruptedException {
        applicationContext = new ClassPathXmlApplicationContext(new String[] {"spring/seata-tcc.xml"});

        tccTransactionService = (TccTransactionService) applicationContext.getBean("tccTransactionService"   );

        tccActionOne = (TccActionOneImpl) applicationContext.getBean("tccActionOne");
        tccActionTwo = (TccActionTwoImpl) applicationContext.getBean("tccActionTwo");

        //分布式事务提交demo
        transactionCommitDemo();

        //分布式事务回滚demo
        transactionRollbackDemo();

        new ApplicationKeeper(applicationContext).keep();
    }

    private static void transactionCommitDemo() throws InterruptedException {
        String txId = tccTransactionService.doTransactionCommit();
        System.out.println(txId);
        Assert.isTrue(StringUtils.isNotBlank(txId), "事务开启失败");

        Thread.sleep(1000L);

        Assert.isTrue("T".equals(ResultHolder.getActionOneResult(txId)), "tccActionOne commit failed");
        Assert.isTrue("T".equals(ResultHolder.getActionTwoResult(txId)), "tccActionTwo commit failed");

        System.out.println("transaction commit demo finish.");
    }

    private static void transactionRollbackDemo() throws InterruptedException {
        Map<String, String> map = new HashMap<>(16);
        try{
            tccTransactionService.doTransactionRollback(map);
            Assert.isTrue(false, "分布式事务未回滚");
        }catch (Throwable t) {
            Assert.isTrue(true, "分布式事务异常回滚");
        }
        String txId = (String) map.get("xid");
        Thread.sleep(1000L);

        Assert.isTrue("R".equals(ResultHolder.getActionOneResult(txId)), "tccActionOne commit failed");
        Assert.isTrue("R".equals(ResultHolder.getActionTwoResult(txId)), "tccActionTwo commit failed");

        System.out.println("transaction rollback demo finish.");
    }

}
