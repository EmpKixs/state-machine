package com.kixs.statemachine.machine;

import com.kixs.statemachine.dao.OrderDataBase;
import com.kixs.statemachine.event.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

import javax.annotation.Resource;

/**
 * 注解方式处理
 *
 * @author wangbing
 * @version v1.0.0
 * @date 2019/11/23 14:53
 */
@Slf4j
// @WithStateMachine
public class OrderStateMachine {

    @Resource
    private OrderDataBase orderDataBase;

    @OnTransition(source = "UNPAID", target = "DONE")
    public void toPaid(Message<OrderEvent> message) {
        log.info("订单完成-message={}", message);
    }

    @OnTransition(source = "UNPAID", target = "CALLED_OFF")
    public void toCancel(Message<OrderEvent> message) {
        log.info("订单取消-message={}", message);
    }

    @OnTransition(target = "UNPAID")
    public void toCreate(Message<OrderEvent> message) {
        log.info("订单创建-message={}", message);
    }
}
