package com.demo.rs;

/**
 * @description: 状态变更记录对象
 * @author: 范帅兵
 * @create: 2020-11-25 23:49
 **/
public class Response {
    /**
     * 上一个状态
     */
    private String preState;
    /**
     * 当前状态
     */
    private String currentState;

    public String getPreState() {
        return preState;
    }

    public void setPreState(String preState) {
        this.preState = preState;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }
}
