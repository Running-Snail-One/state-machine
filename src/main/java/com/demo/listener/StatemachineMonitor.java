package com.demo.listener;

import com.demo.constant.NsiteEvents;
import com.demo.constant.NsiteStates;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.annotation.OnStateChanged;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 动作监听
 */
@WithStateMachine(id = "turnstileStateMachine")
public class StatemachineMonitor {

    @OnTransition(target = "STATE_1")
    public void toState1() {
        System.out.println("STATE_1");
    }

    @OnTransition(source = "STATE_1",target = "STATE_2")
    public void toState2() {
        System.out.println("STATE_1 -> STATE_2");
    }

    @OnTransition(source = "STATE_2",target = "STATE_3")
    public void toState3() {
        System.out.println("STATE_2 -> STATE_3");
    }
}


//@Component
//public class StatemachineMonitor extends StateMachineListenerAdapter<NsiteStates, NsiteEvents> {
//    @Override
//    public void stateChanged(State<NsiteStates, NsiteEvents> from, State<NsiteStates, NsiteEvents> to) {
//        System.out.println(Thread.currentThread().getName() + " | 状态机监听器：stateChanged | from:" + from + " -> to:" + to);
//    }
//
//    @Override
//    public void stateContext(StateContext<NsiteStates, NsiteEvents> stateContext) {
//        if (stateContext.getStage().equals(StateContext.Stage.STATE_CHANGED) && !stateContext.getTarget().getId().equals(NsiteEvents.EVENT_1)) {
//            Object orderId = ((Map) stateContext.getMessageHeaders().get("paramMap")).get("orderId");
//            System.out.println(Thread.currentThread().getName() + " | 状态机监听器->业务订单号:" + orderId);
//        }
//    }
//
//    @Override
//    public void eventNotAccepted(Message<NsiteEvents> event) {
//        System.err.println(Thread.currentThread().getName() + " | this event is not accepted ");
//    }
//}
