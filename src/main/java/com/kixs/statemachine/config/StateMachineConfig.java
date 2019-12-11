package com.kixs.statemachine.config;

import com.kixs.statemachine.action.OrderCancelAction;
import com.kixs.statemachine.action.OrderPaidAction;
import com.kixs.statemachine.event.OrderEvent;
import com.kixs.statemachine.guard.OrderCancelActionGuard;
import com.kixs.statemachine.guard.OrderPaidActionGuard;
import com.kixs.statemachine.machine.OrderStatePersist;
import com.kixs.statemachine.state.OrderState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import javax.annotation.Resource;
import java.util.EnumSet;

/**
 * 状态机配置
 *
 * @author wangbing
 * @version v1.0.0
 * @date 2019/11/23 13:45
 */
@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderState, OrderEvent> {

    @Resource
    private OrderCancelAction orderCancelAction;

    @Resource
    private OrderPaidAction orderPaidAction;

    @Resource
    private OrderCancelActionGuard orderCancelActionGuard;

    @Resource
    private OrderPaidActionGuard orderPaidActionGuard;

    @Resource
    private OrderStatePersist orderStatePersist;

    @Bean("stateMachinePersister")
    public StateMachinePersister<OrderState, OrderEvent, String> orderStatePersist() {
        return new DefaultStateMachinePersister<>(orderStatePersist);
    }

    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
        states.withStates()
                .initial(OrderState.UNPAID)
                .states(EnumSet.allOf(OrderState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
        transitions
                .withExternal()
                    .source(OrderState.UNPAID)
                    .target(OrderState.CALLED_OFF)
                    .event(OrderEvent.CANCEL)
                    .action(orderCancelAction)
                    .guard(orderCancelActionGuard)
                .and().withExternal()
                    .source(OrderState.UNPAID)
                    .target(OrderState.DONE)
                    .event(OrderEvent.PAID)
                    .action(orderPaidAction)
                    .guard(orderPaidActionGuard);
    }
}
