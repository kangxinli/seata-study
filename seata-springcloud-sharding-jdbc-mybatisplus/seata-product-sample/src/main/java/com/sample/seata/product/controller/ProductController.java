package com.sample.seata.product.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.seata.product.service.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PutMapping("/minus/stock")
    public ResponseEntity<Void> minusStock(){
        productService.minusStock();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}
