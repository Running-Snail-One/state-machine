package com.demo;


import com.demo.config.MakeStateMachine;
import com.demo.model.Events;
import com.demo.model.Transition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication()
public class Application implements CommandLineRunner {

    @Autowired
    private Events events;

    @Autowired
    private Transition transition;

    @Autowired
    private StateMachine stateMachine;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        stateMachine.start();
    }

}
