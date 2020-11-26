package com.demo.config;

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
public class StatesConfig {

    /**
     * 初始状态
     */
    private String initState;

    /**
     * 最终状态
     */
    private String endState;

    /**
     * 全量状态
     */
    private List<String> states;

    public String getInitState() {
        return initState;
    }

    public void setInitState(String initState) {
        this.initState = initState;
    }

    public String getEndState() {
        return endState;
    }

    public void setEndState(String endState) {
        this.endState = endState;
    }

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
