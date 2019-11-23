package com.kixs.statemachine.config;

import com.kixs.statemachine.action.OrderCancelAction;
import com.kixs.statemachine.action.OrderCreateAction;
import com.kixs.statemachine.action.OrderPaidAction;
import com.kixs.statemachine.state.OrderEvent;
import com.kixs.statemachine.state.OrderState;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import javax.annotation.Resource;
import java.util.EnumSet;

/**
 * TODO 功能描述
 *
 * @author wangbing
 * @version v1.0.0
 * @date 2019/11/23 13:45
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig extends StateMachineConfigurerAdapter<OrderState, OrderEvent> {

    @Resource
    private OrderCancelAction orderCancelAction;

    @Resource
    private OrderCreateAction orderCreateAction;

    @Resource
    private OrderPaidAction orderPaidAction;



    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
        states.withStates()
                .initial(OrderState.UNPAID)
                .states(EnumSet.allOf(OrderState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
        transitions
                .withExternal().target(OrderState.UNPAID).action(orderCreateAction)
                .and().withExternal().source(OrderState.UNPAID).target(OrderState.CALLED_OFF).action(orderCancelAction)
                .and().withExternal().source(OrderState.UNPAID).target(OrderState.DONE).action(orderPaidAction);
    }
}
