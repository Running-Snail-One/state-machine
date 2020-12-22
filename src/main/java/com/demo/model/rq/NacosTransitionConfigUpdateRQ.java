package com.demo.model.rq;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.List;

/**
 * @Classname NacosTransitionConfigUpdateRQ
 * @Description
 * @Date 2020/12/1 14:21
 * @Created by fanshuaibing
 */
@ApiModel(value = "流转状态",description = "用于更新流转状态")
public class NacosTransitionConfigUpdateRQ {


    @ApiModelProperty(name = "originConfigEntityLists",value = "待更新的流转状态", required = true)
    @Valid
    List<TransitionRQ> originConfigEntityLists;

    @ApiModelProperty(name = "targetConfigEntityLists",value = "目标流转状态", required = true)
    @Valid
    List<TransitionRQ> targetConfigEntityLists;

    public List<TransitionRQ> getOriginConfigEntityLists() {
        return originConfigEntityLists;
    }

    public void setOriginConfigEntityLists(List<TransitionRQ> originConfigEntityLists) {
        this.originConfigEntityLists = originConfigEntityLists;
    }

    public List<TransitionRQ> getTargetConfigEntityLists() {
        return targetConfigEntityLists;
    }

    public void setTargetConfigEntityLists(List<TransitionRQ> targetConfigEntityLists) {
        this.targetConfigEntityLists = targetConfigEntityLists;
    }
}
