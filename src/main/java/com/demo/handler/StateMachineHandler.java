package com.demo.handler;

import com.demo.constant.NsiteEvents;
import com.demo.constant.NsiteStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 范帅兵
 * @create: 2020-11-23 22:48
 **/
@Component
public class StateMachineHandler {
    private final Object object = new Object();
    @Autowired
    private StateMachine<NsiteStates, NsiteEvents> stateMachine;
    public void sendEvent(NsiteStates oldStatus, NsiteEvents event, Map<String,Object> paramMap){
        Message<NsiteEvents> message = MessageBuilder
                .withPayload(event)
                .setHeaderIfAbsent("paramMap",paramMap)
                .build();
        synchronized (object){
            if (!stateMachine.getState().getId().equals(oldStatus)){
                stateMachine.stop();
                List<StateMachineAccess<NsiteStates, NsiteEvents>> withAllRegions = stateMachine.getStateMachineAccessor().withAllRegions();
                for (StateMachineAccess<NsiteStates, NsiteEvents> stateMachineAccess:withAllRegions) {
                    stateMachineAccess.resetStateMachine(new DefaultStateMachineContext<>(oldStatus,null,null,null));
                }
                stateMachine.start();
            }
            stateMachine.sendEvent(message);
        }
    }
}
