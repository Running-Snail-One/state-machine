package com.demo.controller;

import com.demo.config.StateMachineGenerator;
import com.demo.dao.RedisStateMachineRepository;
import com.demo.model.Order;
import com.demo.model.rq.ManuscriptRQ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "订单状态机Controller api测试")
public class OrderTestController {
    @Autowired
    private StateMachineGenerator stateMachineGenerator;

    @Autowired
    private RedisStateMachineRepository redisStateMachineRepository;

    @PostMapping(value = "/testManuscriptState")
    @ApiOperation(value = "测试订单状态")
    public void testOrderState(@RequestBody @ApiParam(name = "稿件请求状态机参数定义",required = true) ManuscriptRQ manuscriptRQ) throws Exception {

        StateMachine<String, String> stateMachine = stateMachineGenerator.getStateMachine();

        Message<String> message = MessageBuilder.withPayload("FIRST_SUBMIT")
                .setHeader("manuscript", manuscriptRQ).build();
        stateMachine.sendEvent(message);

        Message<String> message1 = MessageBuilder.withPayload("SUBMIT_FIRST_AUDIT")
                .setHeader("manuscript", manuscriptRQ).build();
        stateMachine.sendEvent(message);

        Message<String> message2 = MessageBuilder.withPayload("FIRST_AUDIT_2_SECOND_AUDIT")
                .setHeader("manuscript", manuscriptRQ).build();
        stateMachine.sendEvent(message);

        Message<String> message3 = MessageBuilder.withPayload("SELECTED_2_TOPIC_POOL")
                .setHeader("manuscript", manuscriptRQ).build();
        stateMachine.sendEvent(message);

        Message<String> message4 = MessageBuilder.withPayload("SELECTED_2_TOPIC_POOL_END")
                .setHeader("manuscript", manuscriptRQ).build();
        stateMachine.sendEvent(message);

        // 获取最终状态
        System.out.println("最终状态：" + stateMachine.getState().getId());
    }

    @RequestMapping(value = "/testRedisPersister",method = RequestMethod.GET)
    @ApiOperation(value = "保存状态机")
    public void testRedisPersister(String id) throws Exception {
        StateMachine<String, String> stateMachine = stateMachineGenerator.getStateMachine();
        Order order = new Order();
        order.setOrderId(id);
        //发送PAY事件
        Message<String> message = MessageBuilder.withPayload("ITEMS_BUTTON").setHeader("order", order).build();
        //持久化stateMachine
        redisStateMachineRepository.sendEventAndpersist(stateMachine,message, order.getOrderId());
    }

    @RequestMapping(value = "/testRedisPersisterRestore", method = RequestMethod.GET)
    @ApiOperation(value = "从redis中获取状态机状态")
    public void testRestore(String id) throws Exception {
        StateMachine<String, String> stateMachine = stateMachineGenerator.getStateMachine();
        System.out.println("恢复前状态机状态为：" + stateMachine.getState().getId());
        redisStateMachineRepository.restore(stateMachine,id);
        System.out.println("恢复状态机后的状态为：" + stateMachine.getState().getId());
    }
}
