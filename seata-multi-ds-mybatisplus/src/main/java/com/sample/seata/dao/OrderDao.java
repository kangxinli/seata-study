package com.sample.seata.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sample.seata.common.order.Order;

@Mapper
public interface OrderDao extends BaseMapper<Order> {

}
