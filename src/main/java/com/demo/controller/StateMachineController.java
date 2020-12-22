package com.demo.controller;

import com.demo.config.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @description: 状态机controlleår层构建
 * @author: 范帅兵
 * @create: 2020-11-24 23:00
 **/
@RestController
@Api(tags = "状态机属性定义文档")
public class StateMachineController {

    @Autowired
    private StatesConfig statesConfig;
    @Autowired
    private EventsConfig eventsConfig;
    @Autowired
    private TransitionConfig transitionConfig;
    @Autowired
    private StateMachineGenerator stateMachineGenerator;
    @Resource(name = "OrderSsm")
    private StateMachineFactory<OrderStatesEnum, OrderEventsEnum> OrderMachineFactory;

    @ApiOperation(value = "状态机全量状态")
    @RequestMapping(value = "/states",method = RequestMethod.GET)
    public List<String> getStates() throws Exception {
        return  statesConfig.getStates();
    }

    @ApiOperation(value = "状态机全量事件")
    @RequestMapping(value = "/events",method = RequestMethod.GET)
    public List<String> getEvents() throws Exception {
        return  eventsConfig.getEvents();
    }

    @ApiOperation(value = "状态机流转状态")
    @RequestMapping(value = "/transtions",method = RequestMethod.GET)
    public List<Map<String, String>> getTranstions() throws Exception {
        return  transitionConfig.getTransition();
    }

    @ApiOperation(value = "发起事件")
    @ApiImplicitParams(@ApiImplicitParam(name = "event",value = "事件"
            ,dataType = "String",required = true,defaultValue = "ITEMS_BUTTON"))
    @RequestMapping(value = "/event",method = RequestMethod.GET)
    public boolean sendEvent(@RequestParam(value = "event", required = true) String event) throws Exception {
        System.out.println("当前接收到入参：" + event);
        return  stateMachineGenerator.getStateMachine().sendEvent(event);
    }

    @ApiOperation(value = "测试状态机交互示例")
    @ApiImplicitParams(@ApiImplicitParam(name = "event",value = "事件"
            ,dataType = "String",required = true,defaultValue = "ITEMS_BUTTON"))
    @RequestMapping(value = "/interaction/event",method = RequestMethod.GET)
    public void interEvent() throws Exception {

        StateMachine<OrderStatesEnum, OrderEventsEnum> orderDtateMachine = OrderMachineFactory.getStateMachine("SsmOrderFactoryId");
        orderDtateMachine.start();
        orderDtateMachine.sendEvent(OrderEventsEnum.OPEN);
        orderDtateMachine.sendEvent(OrderEventsEnum.ADD);
        orderDtateMachine.sendEvent(OrderEventsEnum.PAY);
    }
}
