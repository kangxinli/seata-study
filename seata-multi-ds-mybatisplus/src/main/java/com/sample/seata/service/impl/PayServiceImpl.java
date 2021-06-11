package com.sample.seata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sample.seata.common.pay.Account;
import com.sample.seata.config.DataSourceKey;
import com.sample.seata.config.DynamicDataSourceContextHolder;
import com.sample.seata.dao.AccountDao;
import com.sample.seata.service.PayService;

import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PayServiceImpl implements PayService {

    @Autowired
    private AccountDao accountDao;

    /**
     * 事务传播特性设置为 REQUIRES_NEW 开启新的事务
     *
     * @param userId 用户 ID
     * @param price  扣减金额
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean reduceBalance(Long userId, Integer price) throws Exception {
        log.info("=============PAY=================");
        DynamicDataSourceContextHolder.setDataSourceKey(DataSourceKey.PAY);
        log.info("当前 XID: {}", RootContext.getXID());

        checkBalance(userId, price);

        log.info("开始扣减用户 {} 余额", userId);
        Account account = accountDao.selectById(userId);

        account.setBalance(account.getBalance() - price);
        Integer record = accountDao.updateById(account);
        log.info("扣减用户 {} 余额结果:{}", userId, record > 0 ? "操作成功" : "扣减余额失败");

        return record > 0;

    }

    private void checkBalance(Long userId, Integer price) throws Exception {
        log.info("检查用户 {} 余额", userId);
        Account account = accountDao.selectById(userId);

        Integer balance = account.getBalance();

        if (balance < price) {
            log.warn("用户 {} 余额不足，当前余额:{}", userId, balance);
            throw new Exception("余额不足");
        }

    }

}

