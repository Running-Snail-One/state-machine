package com.demo.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@PropertySource(value = {"application.yml"})
@ConfigurationProperties(prefix = "nsite")
public class Transition {

    private List<Map<String,String>> transition;

    public List<Map<String, String>> getTransition() {
        return transition;
    }

    public void setTransition(List<Map<String, String>> transition) {
        this.transition = transition;
    }
}
