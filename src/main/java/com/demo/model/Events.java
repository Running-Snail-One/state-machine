package com.demo.model;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource(value = {"application.yml"})
@ConfigurationProperties(prefix = "nsite")
public class Events {

    private List<String> events;

    public List<String> getStates() {
        return events;
    }

    public void setStates(List<String> states) {
        this.events = states;
    }

    @Override
    public String toString() {
        return "Events{" +
                "events=" + events +
                '}';
    }
}
