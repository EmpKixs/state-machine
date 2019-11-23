package com.kixs.statemachine.state;

import com.kixs.statemachine.OrderDataBase;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

import javax.annotation.Resource;

/**
 * TODO 功能描述
 *
 * @author wangbing
 * @version v1.0.0
 * @date 2019/11/23 14:53
 */
@WithStateMachine
public class OrderStateMachine {

    @Resource
    private OrderDataBase orderDataBase;

    /*@OnTransition(source = "UNPAID", target = "DONE")
    public void toPaid(OrderEvent event) {
        orderDataBase.update(event.getOrderId(), OrderState.DONE);
        System.out.println("订单完成");
    }

    @OnTransition(source = "UNPAID", target = "CALLED_OFF")
    public void toCancel(OrderEvent event) {
        orderDataBase.update(event.getOrderId(), OrderState.CALLED_OFF);
        System.out.println("订单取消");
    }

    @OnTransition(target = "UNPAID")
    public void toCreate(OrderEvent event) {
        orderDataBase.create(event.getOrderId());
        System.out.println("订单创建");
    }*/

    @OnTransition(source = "UNPAID", target = "DONE")
    public void toPaid() {
        System.out.println("订单完成");
    }

    @OnTransition(source = "UNPAID", target = "CALLED_OFF")
    public void toCancel() {
        System.out.println("订单取消");
    }

    @OnTransition(target = "UNPAID")
    public void toCreate() {
        System.out.println("订单创建");
    }
}
