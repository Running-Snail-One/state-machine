package com.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import java.util.List;

@RefreshScope
@Configuration
@PropertySource(value = {"application.yml"})
@ConfigurationProperties(prefix = "nsite")
public class StatesConfig {

    /**
     * 初始状态
     */
    private String _initState;

    /**
     * 最终状态
     */
    private String _endState;

    /**
     * 全量状态
     */
    private List<String> _states;

    public String getInitState() {
        return _initState;
    }

    public void setInitState(String initState) {
        this._initState = initState;
    }

    public String getEndState() {
        return _endState;
    }

    public void setEndState(String endState) {
        this._endState = endState;
    }

    public List<String> getStates() {
        return _states;
    }

    public void setStates(List<String> states) {
        this._states = states;
    }

    @Override
    public String toString() {
        return "States{" +
                "states=" + _states +
                '}';
    }
}
