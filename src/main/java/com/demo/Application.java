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
    private MakeStateMachine makeStateMachine;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        StateMachine<String, String> stateMachine = makeStateMachine.getStateMachine();
//        stateMachine.sendEvent(MessageBuilder
//                .withPayload("ITEMS_BUTTON")
//                .setHeader("testStateMachine", "测试头部") // header中可以存放相关数据信息，
//                // 这些信息，在执行过程中，可以在监听器和拦截器中获取到，通过拦截器你可以在做额外的一些事情
//                .build());
    }

}
