//package com.demo.dao;
//
//import com.demo.model.NsiteEvents;
//import com.demo.model.NsiteStates;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.statemachine.StateMachine;
//import org.springframework.statemachine.persist.StateMachinePersister;
//import org.springframework.stereotype.Repository;
//
//import java.util.Random;
//import java.util.UUID;
//
///**
// * 状态机redis持久化操作封装类
// */
//@Repository
//public class RedisStateMachineRepository {
//
//    @Autowired
//    private StateMachine<NsiteStates, NsiteEvents> stateMachine;
//
//    @Autowired
//    private StateMachinePersister<NsiteStates, NsiteEvents, String> stateMachinePersister;
//
//
//    /**
//     * 状态持久化
//     * @throws Exception
//     */
//    public void sendEventAndpersist(String event, String str) throws Exception {
//        stateMachine.sendEvent(NsiteEvents.valueOf(event));
//        System.out.println("持久化对象： " + stateMachine);
//        stateMachinePersister.persist(stateMachine, str);
//    }
//
//    /**
//     * 状态重置
//     * @param user
//     * @return
//     * @throws Exception
//     */
//    public StateMachine<NsiteStates, NsiteEvents> resetStateMachineFromStore(String user) throws Exception {
//        StateMachine<NsiteStates, NsiteEvents> restore = stateMachinePersister.restore(stateMachine, "testprefix:" + user);
//        System.out.println("重置对象后的状态： " + restore);
//        return restore;
//    }
//
//    public void feedMachine(String user, NsiteEvents event) throws Exception {
//        stateMachine.sendEvent(event);
//        stateMachinePersister.persist(stateMachine, "testprefix:" + user);
//    }
//}
