package com.sample.seata.common.pay;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Account {

    private Long id;

    private Integer balance;

    private Date lastUpdateTime;
}
