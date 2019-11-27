package com.kixs.statemachine.action;

import com.kixs.statemachine.dao.OrderDataBase;
import com.kixs.statemachine.event.OrderEvent;
import com.kixs.statemachine.state.OrderState;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Component
public class OrderPaidAction implements Action<OrderState, OrderEvent> {

    @Resource
    private OrderDataBase orderDataBase;

    @Override
    public void execute(StateContext<OrderState, OrderEvent> context) {
        log.info("收到订单支付完成消息，开始支付业务处理");
        log.info("订单支付完成-context={}", context);
    }
}
