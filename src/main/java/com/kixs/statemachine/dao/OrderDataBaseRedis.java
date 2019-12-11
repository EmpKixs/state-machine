package com.kixs.statemachine.dao;

import com.kixs.statemachine.state.OrderState;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 远程Redis实现
 *
 * @author wangbing
 * @version v1.0.0
 * @date 2019/12/11 10:27
 */
public class OrderDataBaseRedis implements OrderDataBase {

    private final static String ORDER_TABLE = "order_table:";

    private String buildOrderRecordKey(String orderId) {
        return ORDER_TABLE + orderId;
    }

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> get(String orderId) {
        String orderKey = buildOrderRecordKey(orderId);
        if (!Boolean.TRUE.equals(redisTemplate.hasKey(orderKey))) {
            throw new RuntimeException("订单不存在");
        }
        Map<String, Object> data = new HashMap<>();
        data.put(ORDER_AMOUNT, redisTemplate.opsForHash().get(orderKey, ORDER_AMOUNT));
        data.put(ORDER_STATE, redisTemplate.opsForHash().get(orderKey, ORDER_STATE));
        data.put(ORDER_TIME, redisTemplate.opsForHash().get(orderKey, ORDER_TIME));

        return data;
    }

    @Override
    public OrderState getState(String orderId) {
        Map<String, Object> data = get(orderId);
        return OrderState.valueOf(data.get(OrderDataBase.ORDER_STATE).toString());
    }

    @Override
    public Map<String, Object> create(String orderId) {
        String orderKey = buildOrderRecordKey(orderId);
        if (Boolean.TRUE.equals(redisTemplate.hasKey(orderKey))) {
            throw new RuntimeException("订单已存在");
        }
        Map<String, Object> data = new HashMap<>(16);
        data.put(ORDER_AMOUNT, BigDecimal.ONE);
        data.put(ORDER_STATE, OrderState.UNPAID);
        data.put(ORDER_TIME, new Date());
        redisTemplate.opsForHash().putAll(orderKey, data);

        return get(orderId);
    }

    @Override
    public Map<String, Object> update(String orderId, OrderState target) {
        Map<String, Object> data = get(orderId);

        data.put(ORDER_STATE, target);
        data.put(ORDER_TIME, new Date());

        redisTemplate.opsForHash().putAll(buildOrderRecordKey(orderId), data);

        return data;
    }
}
