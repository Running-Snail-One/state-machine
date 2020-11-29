package com.demo.dao;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;
import org.springframework.statemachine.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.redis.RedisStateMachinePersister;

@Configuration
public class PersistHandlerConfig {
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public StateMachinePersist<String, String, String> stateMachinePersist(RedisConnectionFactory connectionFactory) {
        RedisStateMachineContextRepository<String, String> repository =
                new RedisStateMachineContextRepository<String, String>(connectionFactory);
        return new RepositoryStateMachinePersist<String, String>(repository);
    }

    @Bean(name = "orderRedisPersister")
    public RedisStateMachinePersister<String, String> redisStateMachinePersister(
            StateMachinePersist<String, String, String> stateMachinePersist) {
        return new RedisStateMachinePersister<String, String>(stateMachinePersist);
    }
}
