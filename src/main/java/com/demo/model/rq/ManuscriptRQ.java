package com.demo.model.rq;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Classname ManuscriptRQ
 * @Description 稿件请求状态机参数定义
 * @Date 2020/12/3 14:05
 * @Created by fanshuaibing
 */
@ApiModel(value = "稿件",description = "稿件入参请求定义")
public class ManuscriptRQ {

    @ApiModelProperty(name = "manuscriptId",value = "稿件ID",required = true)
    @NotBlank(message = "稿件ID不能为空")
    private String  manuscriptId;

    @ApiModelProperty(name = "event", value = "稿件触发事件", required = true)
    @NotBlank(message = "稿件触发器不能为空")
    private String  event;

    @ApiModelProperty(name = "稿件状态不能为空")
    @NotBlank(message = "稿件状态不能为空")
    private String state;

    public String getManuscriptId() {
        return manuscriptId;
    }

    public void setManuscriptId(String manuscriptId) {
        this.manuscriptId = manuscriptId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
