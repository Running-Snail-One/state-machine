package com.demo.model.rq;

import javax.validation.Valid;
import java.util.List;

/**
 * @Classname NacosTransitionConfigUpdateRQ
 * @Description
 * @Date 2020/12/1 14:21
 * @Created by fanshuaibing
 */
public class NacosTransitionConfigUpdateRQ {

    @Valid
    List<TransitionRQ> originConfigEntityLists;
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
