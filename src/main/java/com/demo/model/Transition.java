package com.demo.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@PropertySource(value = {"application.yml"})
@ConfigurationProperties(prefix = "nsite")
public class Transition {

    @Value("${nsite.transition}")
    private static List<Map<String, String>> transition;

    public static List<Map<String, String>> getTransition() {
        return transition;
    }

    public void setTransition(List<Map<String, String>> transition) {
        this.transition = transition;
    }
}
