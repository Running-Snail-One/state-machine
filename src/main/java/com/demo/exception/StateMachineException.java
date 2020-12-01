package com.demo.exception;

import com.demo.constant.ResultEnum;

public class StateMachineException extends RuntimeException{
    private Integer code;

    public StateMachineException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public StateMachineException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
