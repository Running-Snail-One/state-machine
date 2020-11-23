package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.StateMachinePersister;

@SpringBootApplication()
public class Application implements CommandLineRunner {

    @Autowired
    private StateMachine<NsiteStates, NsiteEvents> stateMachine;

//    @Autowired
//    private StateMachinePersister stateMachinePersist;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        stateMachine.start();
        System.out.println("--- push ---");
        stateMachine.sendEvent(NsiteEvents.PUSH);



        System.out.println("--- push ---");
        stateMachine.sendEvent(NsiteEvents.PUSH);



        System.out.println("--- coin ---");
        stateMachine.sendEvent(NsiteEvents.COIN);



        System.out.println("--- coin ---");
        stateMachine.sendEvent(NsiteEvents.COIN);

        stateMachine.stop();
    }
}
