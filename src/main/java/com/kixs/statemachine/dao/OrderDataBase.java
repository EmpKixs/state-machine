package com.kixs.statemachine.dao;

import com.kixs.statemachine.state.OrderState;

import java.util.Map;

/**
 * 模拟数据持久化操作
 *
 * @author wangbing
 * @version v1.0.0
 * @date 2019/11/23 15:34
 */
public interface OrderDataBase {

    String ORDER_AMOUNT = "orderAmount";
    String ORDER_STATE = "orderState";
    String ORDER_TIME = "orderTime";

    Map<String, Object> get(String orderId);

    OrderState getState(String orderId);

    Map<String, Object> create(String orderId);

    Map<String, Object> update(String orderId, OrderState target);
}
