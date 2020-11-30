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
public class EventsConfig {

    private List<String> _events;

    public List<String> getEvents() {
        return _events;
    }

    public void setEvents(List<String> states) {
        this._events = states;
    }

    @Override
    public String toString() {
        return "Events{" +
                "events=" + _events +
                '}';
    }
}
