package com.sample.seata.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("product_info")
public class Product {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String productName;

    private Integer stock;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
