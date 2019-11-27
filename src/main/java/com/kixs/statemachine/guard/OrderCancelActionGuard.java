package com.kixs.statemachine.guard;

import com.kixs.statemachine.dao.OrderDataBase;
import com.kixs.statemachine.event.OrderEvent;
import com.kixs.statemachine.state.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 状态转换验证
 *
 * @author wangbing
 * @version v1.0.0
 * @date 2019/11/25 15:10
 */
@Slf4j
@Component
public class OrderCancelActionGuard implements Guard<OrderState, OrderEvent> {

    @Resource
    private OrderDataBase orderDataBase;

    @Override
    public boolean evaluate(StateContext<OrderState, OrderEvent> context) {
        String orderId = context.getMessage().getHeaders().get("orderId").toString();
        OrderState currentState = orderDataBase.getState(orderId);
        log.info("订单[{}]当前状态：{}", orderId, currentState);
        return OrderState.UNPAID.equals(currentState);
    }
}
