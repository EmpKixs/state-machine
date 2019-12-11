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

    String ORDER_ID = "id";
    String ORDER_AMOUNT = "amount";
    String ORDER_STATE = "state";
    String ORDER_TIME = "time";

    Map<String, Object> get(String orderId);

    OrderState getState(String orderId);

    Map<String, Object> create(String orderId);

    Map<String, Object> update(String orderId, OrderState target);
}
