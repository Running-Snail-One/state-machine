package com.demo.model;

/**
 * @Classname Events
 * @Description TODO
 * @Date 2020/11/27 17:48
 * @Created by fanshuaibing
 */
public class Events {

    /**
     * 状态机ID
     */
   private String id;

    /**
     * 可执行事件的角色
     */
   private String role;

    /**
     * 事件
     */
   private String event;

    /**
     * 姓名
     */
   private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Events{" +
                "id='" + id + '\'' +
                ", role='" + role + '\'' +
                ", event='" + event + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
