package com.demo.constant;

public enum ResultEnum {

    SUCCESS(0, "成功"),
    NO_DEFINE_ORIGIN_TRANSITION(1,"未指定源流转状态信息"),
    NO_DEFINE_NEW_TRANSITION(2,"未指定新流转状态信息"),
    OLD_STATE_NULL(3,"源状态未指定"),
    NEW_STATE_NULL(4,"新状态未指定"),
    OLD_EVENT_NULL(5,"源事件未指定"),
    NEW_EVENT_NULL(6,"新事件未指定"),
    NO_FIND_TRANSITION(7,"未找到待删除的流转状态配置"),
    PARAM_SIZE_INCONFORMITY(8,"旧流转状态参数个数与新流转状态参数个数不一致"),
    PARAM_ERROR(9,"更新流转状态配置参数错误"),
    CONFIG_CENTER_NO_TANSITION(10,"配置中心未找到关键key:_transition"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
