package com.demo.config;
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
public class TransitionConfig {

    private  List<Map<String, String>> _transition;

    public List<Map<String, String>> getTransition() {
        return _transition;
    }

    public void setTransition(List<Map<String, String>> _transition) {
        this._transition = _transition;
    }
}
