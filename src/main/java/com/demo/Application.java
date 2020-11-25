package com.demo;


import com.demo.model.NsiteEvents;
import com.demo.model.NsiteStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;

@SpringBootApplication()
public class Application implements CommandLineRunner {

    @Autowired
    private StateMachine<NsiteStates, NsiteEvents> stateMachine;


    @Autowired
    private StateMachinePersister<NsiteStates, NsiteEvents, String> stateMachinePersister;

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
//        stateMachine.stop();
    }

    public void sendTest(){
        stateMachine.sendEvent(NsiteEvents.EVENT_1);
        stateMachine.sendEvent(NsiteEvents.EVENT_2);
        stateMachine.sendEvent(NsiteEvents.EVENT_3);
    }


}
