package com.sample.seata.dao;


import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sample.seata.common.storage.Product;

@Mapper
public interface ProductDao extends BaseMapper<Product> {

}
