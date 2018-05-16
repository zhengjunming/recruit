package com.qg.recruit.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 郑俊铭
 * Date: 2017/12/2
 * Time: 10:16
 * No struggle, talent how to match the willfulness.
 * Description: 正则表达式枚举类
 */
public enum RegexEnum {

    /**
     * 学号的正则表达式
     */
    STUDENT_ID_REGEX("^(3[1|2]1[6|7]00)\\d{4}$"),

    /**
     * 手机号码正则表达式
     */
    PHONE_REGEX("^1(3|4|5|7|8)\\d{9}$"),

    /**
     * 绩点正则表达式，允许整数，一个小数，两个小数
     */
    GPA_REGEX("^[0-9]+([.]{1}[0-9]{1,2})?$")
    ;

    /**
     * 正则表达式
     */
    @Getter
    @Setter
    private String regex;

    RegexEnum(String regex) {
        this.regex = regex;
    }
}
