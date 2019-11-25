package com.kixs.statemachine.event;


import lombok.Getter;

/**
 * 订单事件定义
 *
 * @author wangbing
 * @version v1.0.0
 * @date 2019/11/23 13:58
 */
@Getter
public enum OrderEvent {
    /**
     * 订单事件定义
     */
    PAID(1, "支付事件"),
    CANCEL(2, "取消事件");

    private final int code;

    private final String desc;

    OrderEvent(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
