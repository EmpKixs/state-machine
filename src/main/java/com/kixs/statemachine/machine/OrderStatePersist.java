package com.kixs.statemachine.machine;

import com.kixs.statemachine.dao.OrderDataBase;
import com.kixs.statemachine.event.OrderEvent;
import com.kixs.statemachine.state.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * TODO 功能描述
 *
 * @author wangbing
 * @version v1.0.0
 * @date 2019/11/25 11:05
 */
@Slf4j
@Component
public class OrderStatePersist implements StateMachinePersist<OrderState, OrderEvent, String> {

    @Resource
    private OrderDataBase orderDataBase;

    @Override
    public void write(StateMachineContext<OrderState, OrderEvent> context, String contextObj) throws Exception {
        log.info("write-state：{}", context);
        orderDataBase.update(contextObj, context.getState());
    }

    @Override
    public StateMachineContext<OrderState, OrderEvent> read(String contextObj) throws Exception {
        OrderState state = orderDataBase.getState(contextObj);
        if (Objects.isNull(state)) {
            throw new RuntimeException("订单不存在");
        }
        return new DefaultStateMachineContext<>(state, null, null, null, null, "stateMachine");
    }
}
