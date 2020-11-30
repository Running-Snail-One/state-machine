package com.demo.model.rq;


import com.demo.model.ConfigEntity;

import java.util.List;
import java.util.Map;

/**
 * @Classname NacosConfigUpdateRQ
 * @Description 修改配置中心请求参数
 * @Date 2020/11/30 10:50
 * @Created by fanshuaibing
 */
public class NacosConfigUpdateRQ {

    private String state;
    private String event;
    private List<ConfigEntity> transition;
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public List<ConfigEntity> getTransition() {
        return transition;
    }

    public void setTransition(List<ConfigEntity> transition) {
        this.transition = transition;
    }
}
