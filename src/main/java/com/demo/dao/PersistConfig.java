package com.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

@Configuration
public class PersistConfig {


    @Autowired
    private InMemoryStateMachinePersist inMemoryStateMachinePersist;

    /**
     * 注入StateMachinePersister对象
     *
     * @return
     */
    @Bean(name="orderMemoryPersister")
    public StateMachinePersister<String, String, String> getPersister() {
        return new DefaultStateMachinePersister<>(inMemoryStateMachinePersist);
    }

}