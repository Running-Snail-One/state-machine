package com.demo.controller;

import com.demo.config.EventsConfig;
import com.demo.config.StatesConfig;
import com.demo.config.TransitionConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description: 状态机controlleår层构建
 * @author: 范帅兵
 * @create: 2020-11-24 23:00
 **/
@RestController
@Api(tags = "状态机Controller api文档")
public class StateMachineController {

    @Autowired
    private StatesConfig statesConfig;
    @Autowired
    private EventsConfig eventsConfig;
    @Autowired
    private TransitionConfig transitionConfig;
    @Autowired
    private StateMachine stateMachine;

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
        return  stateMachine.sendEvent(event);
    }
}
