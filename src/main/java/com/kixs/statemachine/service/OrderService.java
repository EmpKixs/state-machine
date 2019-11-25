package com.kixs.statemachine.service;

/**
 * 订单Service
 *
 * @author wangbing
 * @version v1.0.0
 * @date 2019/11/25 14:00
 */
public interface OrderService {

    String get(String orderId);

    String create(String orderId);

    String paid(String orderId);

    String cancel(String orderId);
}
