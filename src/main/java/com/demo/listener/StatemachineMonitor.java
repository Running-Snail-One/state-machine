//package com.demo.listener;
//
//import org.springframework.messaging.Message;
//import org.springframework.statemachine.StateContext;
//import org.springframework.statemachine.StateMachine;
//import org.springframework.statemachine.listener.StateMachineListenerAdapter;
//import org.springframework.statemachine.state.State;
//
///**
// * 动作监听
// */
//public class StatemachineMonitor extends StateMachineListenerAdapter<NsiteStates, NsiteEvents> {
//    @Override
//    public void stateChanged(State<NsiteStates, NsiteEvents> from, State<NsiteStates, NsiteEvents> to) {
//        System.out.println(Thread.currentThread().getName() + " | 状态机监听器：stateChanged | from:" + from + " -> to:" + to);
//    }
//
//    @Override
//    public void stateContext(StateContext<NsiteStates, NsiteEvents> stateContext) {
//    }
//
//    @Override
//    public void eventNotAccepted(Message<NsiteEvents> event) {
//        System.err.println(Thread.currentThread().getName() + " | this event is not accepted ");
//    }
//
//    @Override
//    public void stateMachineStarted(StateMachine<NsiteStates, NsiteEvents> stateMachine) {
//        System.out.println("状态机正在启动......");
//    }
//
//    @Override
//    public void stateMachineStopped(StateMachine<NsiteStates, NsiteEvents> stateMachine) {
//        System.out.println("状态机正在停止.......");
//    }
//}
