package com.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * 状态机配置
 */
@Configuration
@EnableStateMachineFactory
public class StatemachineConfigurer extends EnumStateMachineConfigurerAdapter<NsiteStates, NsiteEvents> {

//    @Autowired
//    private BizStateMachinePersist bizStateMachinePersist;

    @Override
    public void configure(StateMachineStateConfigurer<NsiteStates, NsiteEvents> states)
            throws Exception {
        states
                .withStates()
                // 初识状态：Locked
                .initial(NsiteStates.LOCKED)
                .states(EnumSet.allOf(NsiteStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<NsiteStates, NsiteEvents> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(NsiteStates.UNLOCKED).target(NsiteStates.LOCKED)
                .event(NsiteEvents.COIN).action(customerPassAndLock())
                .and()
                .withExternal()
                .source(NsiteStates.LOCKED).target(NsiteStates.UNLOCKED)
                .event(NsiteEvents.PUSH).action(turnstileUnlock())
        ;
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<NsiteStates, NsiteEvents> config)
            throws Exception {
        config.withConfiguration()
                .machineId("turnstileStateMachine")
        ;
    }

    /**
     * 状态改变后触发的操作
     * @return
     */
    public Action<NsiteStates, NsiteEvents> turnstileUnlock() {
        return context -> System.out.println("解锁旋转门，以便游客能够通过" );
    }
    /**
     * 状态改变后触发的操作
     * @return
     */
    public Action<NsiteStates, NsiteEvents> customerPassAndLock() {
        return context -> System.out.println("当游客通过，锁定旋转门" );
    }

//
//    @Bean
//    public StateMachinePersister<NsiteStates, NsiteEvents, Integer> stateMachinePersist() {
//        return new DefaultStateMachinePersister<>(bizStateMachinePersist);
//    }

}

