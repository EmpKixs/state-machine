package com.kixs.statemachine.action;

import com.kixs.statemachine.OrderDataBase;
import com.kixs.statemachine.state.OrderEvent;
import com.kixs.statemachine.state.OrderState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * TODO 功能描述
 *
 * @author wangbing
 * @version v1.0.0
 * @date 2019/11/23 16:25
 */
@Component
public class OrderCreateAction implements Action<OrderState, OrderEvent> {

    @Resource
    private OrderDataBase orderDataBase;

    @Override
    public void execute(StateContext<OrderState, OrderEvent> context) {
        // orderDataBase.create(context.getEvent().getOrderId());
        System.out.println("订单创建");
    }
}
