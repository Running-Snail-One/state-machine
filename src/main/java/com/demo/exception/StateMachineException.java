package com.demo.exception;

public class StateMachineException extends RuntimeException{
    private Integer code;

    public StateMachineException(String message, Integer code) {
        super(message);
        this.code = code;
    }

}
