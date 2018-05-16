package com.qg.recruit.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 郑俊铭
 * Date: 2017/12/2
 * Time: 17:09
 * No struggle, talent how to match the willfulness.
 * Description: 数字枚举类
 */
public enum NumberEnum {

    /**
     * 数字枚举，0到7
     */
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7);

    @Setter
    @Getter
    private int number;

    NumberEnum(int number) {
        this.number = number;
    }
}


