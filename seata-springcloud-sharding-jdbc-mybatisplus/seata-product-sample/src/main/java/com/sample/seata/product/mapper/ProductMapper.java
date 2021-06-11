package com.sample.seata.product.mapper;


import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sample.seata.product.entity.Product;

public interface ProductMapper extends BaseMapper<Product> {

    @Update("update product_info set stock = stock-1")
    void minusStock();
}
