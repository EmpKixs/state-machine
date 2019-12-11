package com.kixs.statemachine.service;

import java.util.Map;

/**
 * 订单Service
 *
 * @author wangbing
 * @version v1.0.0
 * @date 2019/11/25 14:00
 */
public interface OrderService {

    Map<String, Object> get(String orderId);

    Map<String, Object> create(String orderId);

    Map<String, Object> paid(String orderId);

    Map<String, Object> cancel(String orderId);
}
