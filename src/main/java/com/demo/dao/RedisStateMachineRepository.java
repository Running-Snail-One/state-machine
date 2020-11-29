package com.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Repository;

/**
 * 状态机redis持久化操作封装类
 */
@Repository
public class RedisStateMachineRepository {


    @Autowired
    @Qualifier("orderRedisPersister")
    private StateMachinePersister<String, String, String> stateMachinePersister;


    /**
     * 状态持久化
     * @throws Exception
     */
    public void sendEventAndpersist(StateMachine<String, String> stateMachine,Message event, String str) throws Exception {
        stateMachine.sendEvent(event);
        System.out.println("持久到redis状态为：" + stateMachine.getState().getId());
        stateMachinePersister.persist(stateMachine, str);
    }

    /**
     * 状态重置
     * @param user
     * @return
     * @throws Exception
     */
    public StateMachine<String, String> restore(StateMachine<String, String> stateMachine, String user) throws Exception {
        StateMachine<String, String> restore = stateMachinePersister.restore(stateMachine, user);
        return restore;
    }

}
