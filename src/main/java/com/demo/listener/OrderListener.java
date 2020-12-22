package com.demo.listener;

import com.demo.config.ScoreSsmGenerator;
import com.demo.config.ScoresEventsEnum;
import com.demo.config.ScoresStatesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import javax.annotation.Resource;

/**
 * @Date 2020/12/18 11:24
 * @Created by fanshuaibing
 */
@WithStateMachine(name = "SsmOrderFactoryId")
public class OrderListener {

    @Resource(name = "ScoreSsm")
    private StateMachineFactory<ScoresStatesEnum, ScoresEventsEnum> ScoreMachineFactory;

    @OnTransition(source = "CLOSE", target = "LOOK")
    public void open() {
        System.out.println("打开京东网页");
    }

    @OnTransition(source = "LOOK", target = "CARTS")
    public void add() {
        System.out.println("添加购物车");
    }

    @OnTransition(source = "CARTS", target = "FINISH")
    public void pay() {
        System.out.println("支付成功");
        StateMachine<ScoresStatesEnum, ScoresEventsEnum> scoreSsmId = ScoreMachineFactory.getStateMachine("scoreSsmId");
        scoreSsmId.start();
        scoreSsmId.sendEvent(ScoresEventsEnum.FINISH);

    }
}
