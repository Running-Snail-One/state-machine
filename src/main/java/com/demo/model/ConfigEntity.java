package com.demo.model;

public class ConfigEntity {
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

    public ConfigEntity(String source, String target, String event) {
        this.source = source;
        this.target = target;
        this.event = event;
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

    @Override
    public String toString() {
        return "ConfigEntity{" +
                "source='" + source + '\'' +
                ", target='" + target + '\'' +
                ", event='" + event + '\'' +
                '}';
    }
}
