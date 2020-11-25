package com.demo.config;

import com.demo.interceptor.NsiteStateMachineInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

@Configuration
public class NsiteStateMachine {

    @Autowired
    private StateMachine stateMachine;

    @Bean
    public StateMachine stateMachine(){
        resisterStateMachineInterceptor();
        return stateMachine;
    }

    /**
     * 注册自定义拦截器
     */
    public void resisterStateMachineInterceptor(){
        stateMachine.getStateMachineAccessor().withRegion()
                .addStateMachineInterceptor(new NsiteStateMachineInterceptor());
    }
}
