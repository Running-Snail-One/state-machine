package com.demo.config;

import com.demo.model.ConfigEntity;
import org.springframework.beans.factory.support.StaticListableBeanFactory;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

@Component
public class MakeStateMachine {

    public StateMachine<String,String> getStateMachine() throws Exception {
        StateMachineBuilder.Builder<String,String> builder = StateMachineBuilder.builder();
        builder.configureConfiguration()
                .withConfiguration()
                //添加状态机监听器
//                .listener(new StatemachineMonitor())
                .beanFactory(new StaticListableBeanFactory());//添加构建bean的工厂类，可以自行实现，这里是使用系统的默认

        Collection<ConfigEntity> data = SSMConfig.getConfigEntities();
        HashSet<String> states = new HashSet<String>();
        for (ConfigEntity configEntity : data) {
            states.add(configEntity.getTarget());
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
