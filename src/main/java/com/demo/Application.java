package com.demo;


import com.demo.constant.NsiteEvents;
import com.demo.constant.NsiteStates;
import com.demo.handler.StateMachineHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication()
public class Application implements CommandLineRunner {

    @Autowired
    private StateMachine<NsiteStates, NsiteEvents> stateMachine;

//    @Autowired
//    private StateMachinePersister stateMachinePersist;
//    @Autowired
//    private StateMachineHandler stateMachineHandler;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        stateMachine.start();
        stateMachine.sendEvent(NsiteEvents.EVENT_1);
        stateMachine.sendEvent(NsiteEvents.EVENT_2);
        stateMachine.sendEvent(NsiteEvents.EVENT_3);
        stateMachine.stop();
    }
}
