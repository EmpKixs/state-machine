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
import java.util.Map;

/**
 * 状态机持久化
 *
 * @author wangbing
 * @version v1.0.0
 * @date 2019/11/25 11:05
 */
@Slf4j
@Component
public class OrderStatePersist implements StateMachinePersist<OrderState, OrderEvent, Map<String, Object>> {

    @Resource
    private OrderDataBase orderDataBase;

    @Override
    public void write(StateMachineContext<OrderState, OrderEvent> context, Map<String, Object> contextObj) throws Exception {
        log.info("write-state：{}", context);
        orderDataBase.update(contextObj.get("orderId").toString(), context.getState());
    }

    @Override
    public StateMachineContext<OrderState, OrderEvent> read(Map<String, Object> contextObj) throws Exception {
        OrderState state = OrderState.valueOf(contextObj.get(OrderDataBase.ORDER_STATE).toString());
        return new DefaultStateMachineContext<>(state, null, null, null, null, contextObj.get("orderId").toString());
    }
}
