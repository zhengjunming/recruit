package com.qg.recruit.exception;

import com.qg.recruit.enums.StateEnum;
import lombok.Getter;

/**
 * @author 郑俊铭
 * Date: 2017/12/1
 * Time: 22:30
 * No struggle, talent how to match the willfulness.
 * Description: QG招新网站自定义异常
 */
public class RecruitException extends RuntimeException {

    @Getter
    private StateEnum stateEnum;

    public RecruitException(StateEnum stateEnum) {
        super(stateEnum.getInfo());
        this.stateEnum = stateEnum;
    }
}
