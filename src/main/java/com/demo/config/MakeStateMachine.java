package com.demo.config;

import com.demo.listener.StatemachineMonitor;
import com.demo.model.ConfigEntity;
import org.springframework.beans.factory.support.StaticListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

/**
 * 创建状态机
 */
@Configuration
public class MakeStateMachine {

    @Bean
    public StateMachine<String,String> getStateMachine() throws Exception {
        return stateMachine();
    }

    public  StateMachine<String,String> stateMachine() throws Exception {
        StateMachineBuilder.Builder<String,String> builder = StateMachineBuilder.builder();
        builder.configureConfiguration()
                .withConfiguration()
                //添加状态机监听器
                .listener(new StatemachineMonitor())
                .autoStartup(true)
                .beanFactory(new StaticListableBeanFactory());//添加构建bean的工厂类，可以自行实现，这里是使用系统的默认

        Collection<ConfigEntity> data = SSMConfig.getConfigEntities();
        HashSet<String> states = new HashSet<String>();
        for (ConfigEntity configEntity : data) {
            states.add(configEntity.getTarget());
            states.add(configEntity.getSource());
            builder.configureTransitions()
                    .withExternal()
                    .source(configEntity.getSource())
                    .target(configEntity.getTarget())
                    .event(configEntity.getEvent());
        }

        builder.configureStates()
                .withStates()
                .initial(SSMConfig.initState.getState())
                .state(SSMConfig.initState.getState())
                .end(SSMConfig.endState.getState())
                .states(states);

        return builder.build();
    }
}
