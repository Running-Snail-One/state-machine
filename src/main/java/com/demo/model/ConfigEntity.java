package com.demo.model;

import javax.validation.constraints.NotBlank;

public class ConfigEntity {
    /**
     * id
     */
    private String id;
    /**
     * 源状态
     */
    private String source;
    /**
     * 目标状态
     */
    private String target;
    /**
     * 触发的事件
     */
    private String event;
    /**
     * 备注信息
     */
    private String info;
    /**
     * 业务类型
     */
    private int type;

    public ConfigEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ConfigEntity{" +
                "id='" + id + '\'' +
                ", source='" + source + '\'' +
                ", target='" + target + '\'' +
                ", event='" + event + '\'' +
                ", info='" + info + '\'' +
                ", type=" + type +
                '}';
    }
}
