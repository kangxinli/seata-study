package com.sample.seata.product.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.seata.product.mapper.ProductMapper;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public void minusStock() {
        productMapper.minusStock();
    }
}
