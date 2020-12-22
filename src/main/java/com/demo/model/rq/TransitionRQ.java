package com.demo.model.rq;

import com.wordnik.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Classname TransitionRQ
 * @Description TODO
 * @Date 2020/12/1 15:20
 * @Created by fanshuaibing
 */
@ApiModel(value = "流转状态请求对象", description = "用于流转状态的增、删")
public class TransitionRQ {

    @ApiModelProperty(value = "源状态", name = "source", required = true)
    @NotBlank(message = "源状态不能为空")
    private String source;

    @NotBlank(message = "目标状态不能为空")
    @ApiModelProperty(name = "target", value = "目标状态", required = true)
    private String target;

    @ApiModelProperty(name = "event", value = "触发事件", required = true)
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
