package com.kixs.statemachine;

import com.kixs.statemachine.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class StateMachineApplicationTests {

    @Resource
    private OrderService orderService;

    @Test
    void contextLoads() {
        System.out.println("1-创建结果：" + orderService.create("1"));
        System.out.println("2-创建结果：" + orderService.create("2"));
        System.out.println("3-创建结果：" + orderService.create("3"));

        System.out.println("1-支付结果：" + orderService.paid("1"));
        System.out.println("2-支付结果：" + orderService.paid("2"));

        System.out.println("2-取消结果：" + orderService.cancel("2"));
        System.out.println("3-取消结果：" + orderService.cancel("3"));

        System.out.println("1-状态：" + orderService.get("1"));
        System.out.println("2-状态：" + orderService.get("2"));
        System.out.println("3-状态：" + orderService.get("3"));
    }

}
