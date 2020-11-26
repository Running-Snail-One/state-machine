package com.demo.model;

public class Order {


    private String orderId;
    private String city;
    private String phone;
    private String event;

    public Order() {
    }

    public Order(String orderId, String city, String phone, String event) {
        this.orderId = orderId;
        this.city = city;
        this.phone = phone;
        this.event = event;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
