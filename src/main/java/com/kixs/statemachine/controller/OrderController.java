package com.kixs.statemachine.controller;

import com.kixs.statemachine.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 订单相关Controller
 *
 * @author wangbing
 * @version v1.0.0
 * @date 2019/11/25 14:01
 */
@RestController
@RequestMapping("/test")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/{orderId}/get")
    public Map<String, Object> get(@PathVariable("orderId") String orderId) {
        return orderService.get(orderId);
    }

    @GetMapping("/{orderId}/create")
    public Map<String, Object> create(@PathVariable("orderId") String orderId) {
        return orderService.create(orderId);
    }

    @GetMapping("/{orderId}/paid")
    public Map<String, Object> paid(@PathVariable("orderId") String orderId) {
        return orderService.paid(orderId);
    }

    @GetMapping("/{orderId}/cancel")
    public Map<String, Object> cancel(@PathVariable("orderId") String orderId) {
        return orderService.cancel(orderId);
    }
}
