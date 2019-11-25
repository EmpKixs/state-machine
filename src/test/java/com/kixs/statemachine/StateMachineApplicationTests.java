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


    }

}
