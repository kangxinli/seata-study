package com.sample.seata.service;

import com.sample.seata.common.OperationResponse;
import com.sample.seata.common.order.PlaceOrderRequestVO;

public interface OrderService {

    /**
     * 下单
     *
     * @param placeOrderRequestVO 请求参数
     * @return 下单结果
     */
    OperationResponse placeOrder(PlaceOrderRequestVO placeOrderRequestVO) throws Exception;
}
