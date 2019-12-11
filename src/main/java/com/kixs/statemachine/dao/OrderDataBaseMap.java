package com.kixs.statemachine.dao;

import com.kixs.statemachine.state.OrderState;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO 功能描述
 *
 * @author wangbing
 * @version v1.0.0
 * @date 2019/12/11 10:26
 */
public class OrderDataBaseMap implements OrderDataBase {

    private static Map<String, Map<String, Object>> orderMap = new ConcurrentHashMap<>();

    @Override
    public Map<String, Object> get(String orderId) {
        if (!orderMap.containsKey(orderId)) {
            throw new RuntimeException("订单不存在");
        }
        return orderMap.get(orderId);
    }

    @Override
    public OrderState getState(String orderId) {
        Map<String, Object> data = get(orderId);
        return (OrderState) data.get(ORDER_STATE);
    }

    @Override
    public Map<String, Object> create(String orderId) {
        if (orderMap.containsKey(orderId)) {
            throw new RuntimeException("订单已存在");
        }
        Map<String, Object> data = new HashMap<>(16);
        data.put(ORDER_AMOUNT, 100);
        data.put(ORDER_STATE, OrderState.UNPAID);
        data.put(ORDER_TIME, LocalDateTime.now());
        orderMap.put(orderId, data);

        return get(orderId);
    }

    @Override
    public Map<String, Object> update(String orderId, OrderState target) {
        Map<String, Object> data = get(orderId);

        data.put(ORDER_STATE, target);
        data.put(ORDER_TIME, LocalDateTime.now());
        orderMap.put(orderId, data);

        return data;
    }
}
