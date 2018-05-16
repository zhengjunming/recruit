package com.qg.recruit.dto;

import com.qg.recruit.enums.StateEnum;
import lombok.Data;

/**
 * @author 郑俊铭
 * Date: 2017/12/1
 * Time: 22:18
 * No struggle, talent how to match the willfulness.
 * Description: 返回的结果类，含有状态码，状态码所代表的信息，返回的数据
 */
@Data
public class Result<T> {
    /**
     * 状态码
     */
    private int state;

    /**
     * 状态信息
     */
    private String info;

    /**
     * 数据
     */
    private T data;

    public Result(StateEnum stateEnum, T data) {
        this.state = stateEnum.getState();
        this.info = stateEnum.getInfo();
        this.data = data;
    }

    public Result(StateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.info = stateEnum.getInfo();
    }
}
