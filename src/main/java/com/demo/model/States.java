package com.demo.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource(value = {"application.yml"})
@ConfigurationProperties(prefix = "nsite")
public class States{

    private List<String> states;

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "States{" +
                "states=" + states +
                '}';
    }
}
