package com.kixs.statemachine;

import com.kixs.statemachine.dao.OrderDataBase;
import com.kixs.statemachine.dao.OrderDataBaseMap;
import com.kixs.statemachine.dao.OrderDataBaseRedis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(scanBasePackages = "com.kixs")
public class StateMachineApplication {

    public static void main(String[] args) {
        SpringApplication.run(StateMachineApplication.class, args);
    }

    @Bean
    public OrderDataBase buildOrderDataBase() {
        // return new OrderDataBaseMap();
        return new OrderDataBaseRedis();
    }
}
