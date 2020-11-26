package com.demo.model;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@RefreshScope
@Configuration
@PropertySource(value = {"application.yml"})
@ConfigurationProperties(prefix = "nsite")
public class Events {

    private List<String> events;

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> states) {
        this.events = states;
    }

    @Override
    public String toString() {
        return "Events{" +
                "events=" + events +
                '}';
    }
}
