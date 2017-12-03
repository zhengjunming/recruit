package com.qg.recruit.exception;

import com.qg.recruit.enums.StateEnum;

/**
 * @author 郑俊铭
 * Date: 2017/12/1
 * Time: 22:30
 * No struggle, talent how to match the willfulness.
 * Description:
 */
public class RecruitException extends RuntimeException {

    private StateEnum stateEnum;

    public RecruitException(StateEnum stateEnum) {
        super(stateEnum.getInfo());
        this.stateEnum = stateEnum;
    }

    public StateEnum getStateEnum() {
        return stateEnum;
    }
}
