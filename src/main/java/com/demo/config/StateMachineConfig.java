package com.demo.config;

import com.demo.model.NsiteEvents;
import com.demo.model.NsiteStates;
import com.demo.listener.StatemachineMonitor;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * 状态机配置
 */
@Configuration()
@EnableStateMachine(contextEvents = false)
public class StateMachineConfig
        extends EnumStateMachineConfigurerAdapter<NsiteStates, NsiteEvents> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<NsiteStates, NsiteEvents> config)
            throws Exception {

          config.withConfiguration()
                  .listener(new StatemachineMonitor())
                .autoStartup(true)
                .machineId("NsiteStateMachine")
                ;
    }

    @Override
    public void configure(StateMachineStateConfigurer<NsiteStates, NsiteEvents> states)
            throws Exception {
        states.withStates()
                .initial(NsiteStates.STATE_1) //默认初始状态
                .states(EnumSet.allOf(NsiteStates.class));//状态集合
    }

    /**
     * 触发器配置
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<NsiteStates, NsiteEvents> transitions)
            throws Exception {
        //---------------STATE_1  =======>   STATE_2 ==========>EVENT_1
        //---------------STATE_2  =======>   STATE_3 ==========>EVENT_2
        //---------------STATE_2  =======>   STATE_3 ==========>EVENT_3
        transitions
                .withExternal()
                .source(NsiteStates.STATE_1).target(NsiteStates.STATE_1).event(NsiteEvents.EVENT_1)
//                .action(event1Action())
                .and()
                .withExternal()
                .source(NsiteStates.STATE_1).target(NsiteStates.STATE_2).event(NsiteEvents.EVENT_2)
//                .action(event2Action())
                .and()
                .withExternal()
                .source(NsiteStates.STATE_2).target(NsiteStates.STATE_3).event(NsiteEvents.EVENT_3);
//                .action(event3Action());
    }

    /**
     * 状态改变后触发的操作
     * @return
     */
    public Action<NsiteStates, NsiteEvents> event1Action() {
        return context -> System.out.println("STATE_1=======>STATE_2=========>EVENT_1" );
    }
    /**
     * 状态改变后触发的操作
     * @return
     */
    public Action<NsiteStates, NsiteEvents> event2Action() {
        return context -> System.out.println("STATE_2=======>STATE_3=========>EVENT_2" );
    }
    /**
     * 状态改变后触发的操作
     * @return
     */
    public Action<NsiteStates, NsiteEvents> event3Action() {
        return context -> System.out.println("STATE_2=======>STATE_3=========>EVENT_3" );
    }
}