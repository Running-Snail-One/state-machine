package com.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @Date 2020/12/18 10:58
 * @Created by fanshuaibing
 */
@EnableStateMachineFactory(name = "ScoreSsm")
@Configuration
public class ScoreSsmGenerator extends EnumStateMachineConfigurerAdapter<ScoresStatesEnum,ScoresEventsEnum> {
    @Override
    public void configure(StateMachineStateConfigurer<ScoresStatesEnum, ScoresEventsEnum> states)
            throws Exception {
        states
                .withStates()
                .initial(ScoresStatesEnum.NOSCORE)
                .end(ScoresStatesEnum.INCRESE)
                .states(EnumSet.allOf(ScoresStatesEnum.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<ScoresStatesEnum, ScoresEventsEnum> transitions) throws Exception {
        transitions
                .withExternal()
                .source(ScoresStatesEnum.NOSCORE)
                .target(ScoresStatesEnum.INCRESE)
                .event(ScoresEventsEnum.FINISH);
    }
}
