package com.sample.seata.dao;


import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sample.seata.common.pay.Account;

@Mapper
public interface AccountDao extends BaseMapper<Account> {

}