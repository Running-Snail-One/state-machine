package com.demo.dao;

import com.demo.model.NsiteEvents;
import com.demo.model.NsiteStates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;
import org.springframework.statemachine.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.redis.RedisStateMachinePersister;

import java.util.EnumSet;

@Configuration
public class PersistHandlerConfig {
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public StateMachinePersist<NsiteStates, NsiteEvents, String> stateMachinePersist(RedisConnectionFactory connectionFactory) {
        RedisStateMachineContextRepository<NsiteStates, NsiteEvents> repository =
                new RedisStateMachineContextRepository<NsiteStates, NsiteEvents>(connectionFactory);
        return new RepositoryStateMachinePersist<NsiteStates, NsiteEvents>(repository);
    }

    @Bean
    public RedisStateMachinePersister<NsiteStates, NsiteEvents> redisStateMachinePersister(
            StateMachinePersist<NsiteStates, NsiteEvents, String> stateMachinePersist) {
        return new RedisStateMachinePersister<NsiteStates, NsiteEvents>(stateMachinePersist);
    }


    @Bean(name = "stateMachineTarget")
    @Scope(scopeName="prototype")
    public StateMachine<NsiteStates, NsiteEvents> stateMachineTarget() throws Exception {
        StateMachineBuilder.Builder<NsiteStates, NsiteEvents> builder = StateMachineBuilder.<NsiteStates, NsiteEvents>builder();

        builder.configureConfiguration()
                .withConfiguration()
                .autoStartup(true);

        builder.configureStates()
                .withStates()
                .initial(NsiteStates.STATE_1)
                .states(EnumSet.allOf(NsiteStates.class));

        builder.configureTransitions()
                .withInternal()
                .source(NsiteStates.STATE_1).event(NsiteEvents.EVENT_1)
                .and()
                .withInternal()
                .source(NsiteStates.STATE_2).event(NsiteEvents.EVENT_2)
                .and()
                .withInternal()
                .source(NsiteStates.STATE_3).event(NsiteEvents.EVENT_3);
        return builder.build();
    }
}
