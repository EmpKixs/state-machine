package com.kixs.statemachine;

import com.kixs.statemachine.state.OrderEvent;
import com.kixs.statemachine.state.OrderState;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
@SpringBootApplication
public class StateMachineApplication {

    public static void main(String[] args) {
        SpringApplication.run(StateMachineApplication.class, args);
    }

    @Resource
    private StateMachine<OrderState, OrderEvent> stateMachine;

    @Resource
    private OrderDataBase orderDataBase;

    @GetMapping("/{orderId}/get")
    public String get(@PathVariable("orderId") String orderId) {
        return orderDataBase.get(orderId);
    }

    @GetMapping("/{orderId}/create")
    public String create(@PathVariable("orderId") String orderId) {
        OrderEvent event = new OrderEvent();
        event.setOrderId(orderId);
        orderDataBase.create(event.getOrderId());
        /*stateMachine.start();
        boolean res = stateMachine.sendEvent(event);
        System.out.println(res);*/
        return orderDataBase.get(orderId);
    }

    @GetMapping("/{orderId}/paid")
    public String paid(@PathVariable("orderId") String orderId) {
        OrderEvent event = new OrderEvent();
        event.setOrderId(orderId);
        stateMachine.start();
        boolean res = stateMachine.sendEvent(event);
        System.out.println(res);
        return orderDataBase.get(orderId);
    }

    @GetMapping("/{orderId}/cancel")
    public String cancel(@PathVariable("orderId") String orderId) {
        OrderEvent event = new OrderEvent();
        event.setOrderId(orderId);
        stateMachine.start();
        stateMachine.sendEvent(event);
        return orderDataBase.get(orderId);
    }
}
