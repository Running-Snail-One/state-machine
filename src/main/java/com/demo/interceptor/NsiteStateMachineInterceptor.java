package com.demo.interceptor;

import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptor;
import org.springframework.statemachine.transition.Transition;

/**
 * 添加Nsite拦截器，解决状态更新后的状态与数据持久化状态不一致的问题。
 */
public class NsiteStateMachineInterceptor implements StateMachineInterceptor {
    @Override
    public Message preEvent(Message message, StateMachine stateMachine) {
        return message;
    }

    @Override
    public void preStateChange(State state, Message message, Transition transition, StateMachine stateMachine) {

    }

    @Override
    public void postStateChange(State state, Message message, Transition transition, StateMachine stateMachine) {

    }

    @Override
    public StateContext preTransition(StateContext stateContext) {
        return stateContext;
    }

    @Override
    public StateContext postTransition(StateContext stateContext) {
        return stateContext;
    }

    @Override
    public Exception stateMachineError(StateMachine stateMachine, Exception exception) {
        return exception;
    }
}
