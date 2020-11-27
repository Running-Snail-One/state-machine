package com.demo.controller;

import com.demo.dao.RedisStateMachineRepository;
import com.demo.model.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "状态机Controller api文档")
public class OrderController {
    @Autowired
    private StateMachine stateMachine;

    @Autowired
    private RedisStateMachineRepository redisStateMachineRepository;

    @RequestMapping(value = "/testOrderState",method = RequestMethod.GET)
    @ApiOperation(value = "测试订单状态")
    public void testOrderState(String orderId) throws Exception {

        System.out.println(stateMachine.getId());

        // 创建流程
        stateMachine.start();

        // 触发PAY事件
        stateMachine.sendEvent("ITEMS_BUTTON");

        // 触发RECEIVE事件
        Order order = new Order(orderId, "广东省深圳市", "13435465465", "PRODUCT_ADD");
        Message<String> message = MessageBuilder.withPayload("PRODUCT_ADD").setHeader("order", order).build();
        stateMachine.sendEvent(message);

        // 获取最终状态
        System.out.println("最终状态：" + stateMachine.getState().getId());
    }

    @RequestMapping(value = "/testRedisPersister",method = RequestMethod.GET)
    @ApiOperation(value = "保存状态机")
    public void testRedisPersister(String id) throws Exception {
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

        System.out.println("恢复前状态机状态为：" + stateMachine.getState().getId());
        redisStateMachineRepository.restore(stateMachine,id);
        System.out.println("恢复状态机后的状态为：" + stateMachine.getState().getId());
    }
}
