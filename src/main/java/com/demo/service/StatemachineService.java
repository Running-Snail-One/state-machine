package com.demo.service;
import com.demo.constant.NsiteEvents;
import com.demo.constant.NsiteStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class StatemachineService {

    @Autowired
    private StateMachine<NsiteStates, NsiteEvents> stateMachine;
    @Autowired
    private StateMachinePersister<NsiteStates, NsiteEvents, String> stateMachinePersister;

    /**
     * 状态持久化
     * @throws Exception
     */
    public void persist() throws Exception {
        stateMachine.sendEvent(NsiteEvents.EVENT_1);
        stateMachinePersister.persist(stateMachine, "testprefix:" + "admin");
    }

    /**
     * 状态重置
     * @param user
     * @return
     * @throws Exception
     */
    public StateMachine<NsiteStates, NsiteEvents> resetStateMachineFromStore(String user) throws Exception {
        return stateMachinePersister.restore(stateMachine, "testprefix:" + user);
    }

    public void feedMachine(String user, NsiteEvents event) throws Exception {
        stateMachine.sendEvent(event);
        stateMachinePersister.persist(stateMachine, "testprefix:" + user);
    }
}
