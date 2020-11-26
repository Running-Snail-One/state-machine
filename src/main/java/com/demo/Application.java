package com.demo;


import com.demo.config.MakeStateMachine;
import com.demo.config.SSMConfig;
import com.demo.model.Events;
import com.demo.model.States;
import com.demo.model.Transition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
public class Application implements CommandLineRunner {

    @Autowired
    private Events events;

    @Autowired
    private Transition transition;

    @Autowired
    private States states;

    @Autowired
    private SSMConfig ssmConfig;
    @Autowired
    private MakeStateMachine makeStateMachine;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        makeStateMachine.getStateMachine().start();
    }

}
