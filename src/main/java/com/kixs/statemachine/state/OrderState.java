package com.kixs.statemachine.state;

import lombok.Getter;

/**
 * 状态定义
 *
 * @author wangbing
 * @version v1.0.0
 * @date 2019/11/23 13:52
 */
@Getter
public enum OrderState {
    /**
     * 状态定义
     */
    UNPAID(1, "待支付"),
    DONE(2, "已完成"),
    CALLED_OFF(3, "已取消");

    private final int code;

    private final String desc;

    OrderState(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
