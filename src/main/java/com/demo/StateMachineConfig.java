package com.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class StateMachineConfig
        extends EnumStateMachineConfigurerAdapter<NsiteStates, NsiteEvents> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<NsiteStates, NsiteEvents> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<NsiteStates, NsiteEvents> states)
            throws Exception {
        states
                .withStates()
                .initial(NsiteStates.UNLOCKED)
                .states(EnumSet.allOf(NsiteStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<NsiteStates, NsiteEvents> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(NsiteStates.UNLOCKED).target(NsiteStates.UNLOCKED).event(NsiteEvents.COIN)
                .and()
                .withExternal()
                .source(NsiteStates.UNLOCKED).target(NsiteStates.LOCKED).event(NsiteEvents.PUSH);
    }

    @Bean
    public StateMachineListener<NsiteStates, NsiteEvents> listener() {
        return new StateMachineListenerAdapter<NsiteStates, NsiteEvents>() {
            @Override
            public void stateChanged(State<NsiteStates, NsiteEvents> from, State<NsiteStates, NsiteEvents> to) {
                System.out.println("State change to " + to.getId());
            }
        };
    }
}