package com.qg.recruit.enums;

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

    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    NumberEnum(int number) {

        this.number = number;
    }
}


