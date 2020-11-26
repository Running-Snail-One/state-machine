package com.demo.listener;

import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

/**
 * 动作监听
 */
public class StatemachineMonitor extends StateMachineListenerAdapter<String, String> {
    @Override
    public void stateChanged(State<String, String> from, State<String, String> to) {
        System.out.println(Thread.currentThread().getName() + " | 状态机监听器：stateChanged | from:" + from + " -> to:" + to);
    }
    @Override
    public void stateMachineStarted(StateMachine<String, String> stateMachine) {
        System.out.println("状态机启动.....");
    }

    @Override
    public void stateMachineStopped(StateMachine<String, String> stateMachine) {
        System.out.println("状态机正在停止运行.....");
    }

    @Override
    public void eventNotAccepted(Message<String> event) {
        System.out.println("事件未接收到.....");
    }
}
