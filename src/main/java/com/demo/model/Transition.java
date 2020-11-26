package com.demo.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

@RefreshScope
@Configuration
@PropertySource(value = {"application.yml"})
@ConfigurationProperties(prefix = "nsite")
public class Transition {

    private  List<Map<String, String>> transition;

    public  List<Map<String, String>> getTransition() {
        return transition;
    }

    public void setTransition(List<Map<String, String>> transition) {
        this.transition = transition;
    }
}
