package com.demo.config;

import com.demo.listener.StatemachineMonitor;
import com.demo.model.ConfigEntity;
import com.demo.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.StaticListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 创建状态机
 */

@Component
public class MakeStateMachine {

    @Autowired
    private StatesConfig statesConfig;
    @Autowired
    private TransitionConfig transitionConfig;

    @Bean
    public StateMachine<String, String> getStateMachine() throws Exception {
        return stateMachine();
    }

    public StateMachine<String, String> stateMachine() throws Exception {
        StateMachineBuilder.Builder<String, String> builder = StateMachineBuilder.builder();
        builder.configureConfiguration()
                .withConfiguration()
                //添加状态机监听器
                .listener(new StatemachineMonitor())
                .autoStartup(true)
                .beanFactory(new StaticListableBeanFactory());//添加构建bean的工厂类，可以自行实现，这里是使用系统的默认

        Collection<ConfigEntity> data = getEntities();

        /**
         * 状态流转配置
         */
        for (ConfigEntity configEntity : data) {
            builder.configureTransitions()
                    .withExternal()
                    .source(configEntity.getSource())
                    .target(configEntity.getTarget())
                    .event(configEntity.getEvent());
        }
        /**
         * 状态配置
         */
        builder.configureStates()
                .withStates()
                .initial(statesConfig.getInitState())
                .state(statesConfig.getInitState())
                .end(statesConfig.getEndState())
                .states(statesConfig.getStates().stream().collect(Collectors.toSet()));

        return builder.build();
    }


    public  HashSet<ConfigEntity> getEntities(){
        List<ConfigEntity> list = MapUtils.map2List(transitionConfig.getTransition());
        return (HashSet<ConfigEntity>) list.stream().collect(Collectors.toSet());
    }
}
