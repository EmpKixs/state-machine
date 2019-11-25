package com.kixs.statemachine.service.impl;

import com.kixs.statemachine.dao.OrderDataBase;
import com.kixs.statemachine.event.OrderEvent;
import com.kixs.statemachine.service.OrderService;
import com.kixs.statemachine.state.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 订单ServiceImpl
 *
 * @author wangbing
 * @version v1.0.0
 * @date 2019/11/25 14:01
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private StateMachinePersister<OrderState, OrderEvent, String> stateMachinePersister;

    @Resource
    private StateMachineFactory<OrderState, OrderEvent> orderFsm;

    @Resource
    private OrderDataBase orderDataBase;

    @Override
    public String get(String orderId) {
        return orderDataBase.get(orderId);
    }

    @Override
    public String create(String orderId) {
        orderDataBase.create(orderId);
        return orderDataBase.get(orderId);
    }

    @Override
    public String paid(String orderId) {
        StateMachine<OrderState, OrderEvent> stateMachine = orderFsm.getStateMachine(orderId);
        stateMachine.start();
        try {
            stateMachinePersister.restore(stateMachine, orderId);
            MessageBuilder<OrderEvent> builder = MessageBuilder
                    .withPayload(OrderEvent.PAID)
                    .setHeader("orderId", orderId);
            Message<OrderEvent> message = builder.build();
            boolean success = stateMachine.sendEvent(message);
            if (success) {
                stateMachinePersister.persist(stateMachine, orderId);
            } else {
                log.error("状态机处理失败");
            }
        } catch (Exception e) {
            log.error("状态机处理异常", e);
        } finally {
            stateMachine.stop();
        }
        return orderDataBase.get(orderId);
    }

    @Override
    public String cancel(String orderId) {
        StateMachine<OrderState, OrderEvent> stateMachine = orderFsm.getStateMachine(orderId);
        stateMachine.start();
        try {
            stateMachinePersister.restore(stateMachine, orderId);
            MessageBuilder<OrderEvent> builder = MessageBuilder
                    .withPayload(OrderEvent.CANCEL)
                    .setHeader("orderId", orderId);
            Message<OrderEvent> message = builder.build();
            boolean success = stateMachine.sendEvent(message);
            if (success) {
                stateMachinePersister.persist(stateMachine, orderId);
            } else {
                log.error("状态机处理失败");
            }
        } catch (Exception e) {
            log.error("状态机处理异常", e);
        } finally {
            stateMachine.stop();
        }
        return orderDataBase.get(orderId);
    }
}
