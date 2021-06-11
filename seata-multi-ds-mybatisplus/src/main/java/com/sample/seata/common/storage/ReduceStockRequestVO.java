package com.sample.seata.common.storage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 扣减库存请求 VO
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReduceStockRequestVO {

    private Long productId;

    private Integer amount;
}
