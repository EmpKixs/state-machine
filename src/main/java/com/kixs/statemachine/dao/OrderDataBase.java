package com.kixs.statemachine.dao;

import com.kixs.statemachine.state.OrderState;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟数据持久化操作
 *
 * @author wangbing
 * @version v1.0.0
 * @date 2019/11/23 15:34
 */
@Component
public class OrderDataBase {

    private static Map<String, OrderState> orderMap = new ConcurrentHashMap<>();

    public String get(String orderId) {
        OrderState state = orderMap.get(orderId);
        return state == null ? "不存在" : state.getDesc();
    }

    public OrderState getState(String orderId) {
        return orderMap.get(orderId);
    }

    public String create(String orderId) {
        OrderState state = orderMap.get(orderId);
        if (state == null) {
            put(orderId, OrderState.UNPAID);
        }
        return get(orderId);
    }

    public String update(String orderId, OrderState target) {
        OrderState state = orderMap.get(orderId);
        if (state == null) {
            return "异常：不存在";
        }
        put(orderId, target);
        return get(orderId);
    }

    private void put(String orderId, OrderState state) {
        orderMap.put(orderId, state);
    }
}
