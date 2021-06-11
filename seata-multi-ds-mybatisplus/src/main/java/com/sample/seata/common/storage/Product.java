package com.sample.seata.common.storage;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Product {

    private Integer id;

    private Double price;

    private Integer stock;

    private Date lastUpdateTime;
}
