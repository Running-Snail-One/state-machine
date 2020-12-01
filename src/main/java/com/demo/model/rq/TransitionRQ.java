package com.demo.model.rq;

import javax.validation.constraints.NotBlank;

/**
 * @Classname TransitionRQ
 * @Description TODO
 * @Date 2020/12/1 15:20
 * @Created by fanshuaibing
 */
public class TransitionRQ {
    /**
     * 源状态
     */
    @NotBlank(message = "源状态不能为空")
    private String source;
    /**
     * 目标状态
     */
    @NotBlank(message = "目标状态不能为空")
    private String target;
    /**
     * 触发的事件
     */
    private String event;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
