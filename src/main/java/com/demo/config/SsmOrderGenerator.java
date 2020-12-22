package com.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @Date 2020/12/18 10:23
 * @Created by fanshuaibing
 */
@Configuration
@EnableStateMachineFactory(name = "OrderSsm")
public class SsmOrderGenerator extends EnumStateMachineConfigurerAdapter<OrderStatesEnum,OrderEventsEnum> {
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatesEnum, OrderEventsEnum> states)
            throws Exception {
        states
                .withStates()
                .initial(OrderStatesEnum.CLOSE)
                .end(OrderStatesEnum.FINISH)
                .states(EnumSet.allOf(OrderStatesEnum.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatesEnum, OrderEventsEnum> transitions) throws Exception {
        transitions
                .withExternal()
                .source(OrderStatesEnum.CLOSE).target(OrderStatesEnum.LOOK).event(OrderEventsEnum.OPEN)
                .and()
                .withExternal()
                .source(OrderStatesEnum.LOOK).target(OrderStatesEnum.CARTS).event(OrderEventsEnum.ADD)
                .and()
                .withExternal()
                .source(OrderStatesEnum.CARTS).target(OrderStatesEnum.FINISH).event(OrderEventsEnum.PAY);
    }
}
