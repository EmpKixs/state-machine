package com.kixs.statemachine;

import com.kixs.statemachine.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class StateMachineApplicationTests {

    @Resource
    private OrderService orderService;

    @Test
    void contextLoads() {
        log.info("1-创建结果：{}" , orderService.create("1"));
        log.info("2-创建结果：{}" , orderService.create("2"));
        log.info("3-创建结果：{}" , orderService.create("3"));

        log.info("1-支付结果：{}" , orderService.paid("1"));
        log.info("2-支付结果：{}" , orderService.paid("2"));

        log.info("2-取消结果：{}" , orderService.cancel("2"));
        log.info("3-取消结果：{}" , orderService.cancel("3"));

        log.info("1-状态：{}" , orderService.get("1"));
        log.info("2-状态：{}" , orderService.get("2"));
        log.info("3-状态：{}" , orderService.get("3"));
    }

}
