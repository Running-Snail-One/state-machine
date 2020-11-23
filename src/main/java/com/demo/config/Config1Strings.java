//package com.demo.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.statemachine.config.EnableStateMachine;
//import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
//import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
//
//import java.util.Arrays;
//import java.util.HashSet;
//
///**
// * 状态采用set
// */
//@Configuration
//@EnableStateMachine
//public class Config1Strings
//        extends StateMachineConfigurerAdapter<String, String> {
//
//    @Override
//    public void configure(StateMachineStateConfigurer<String, String> states)
//            throws Exception {
//        states
//                .withStates()
//                .initial("STATE_1")
//                .end("SF")
//                .states(new HashSet<String>(Arrays.asList("S1","S2","S3","S4")));
//    }
//
//}
