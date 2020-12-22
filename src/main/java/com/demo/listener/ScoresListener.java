package com.demo.listener;

import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * @Date 2020/12/18 11:28
 * @Created by fanshuaibing
 */
@WithStateMachine(name = "SsmOrderFactoryId")
public class ScoresListener {
    @OnTransition(target = "FINISH")
    public void open() {
        System.out.println("积分增加");
    }

}
