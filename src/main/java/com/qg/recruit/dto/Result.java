package com.qg.recruit.dto;

import com.qg.recruit.enums.StateEnum;

/**
 * @author 郑俊铭
 * Date: 2017/12/1
 * Time: 22:18
 * No struggle, talent how to match the willfulness.
 * Description:
 */
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
