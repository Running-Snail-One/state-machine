package com.demo.controller;

import com.demo.config.MakeStateMachine;
import com.demo.model.Events;
import com.demo.model.States;
import com.demo.model.Transition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @description: 状态机controlleår层构建
 * @author: 范帅兵
 * @create: 2020-11-24 23:00
 **/
@Controller
public class StateMachineController {

    @Autowired
    private States states;
    @Autowired
    private Events events;
    @Autowired
    private Transition transition;
    @Autowired
    private StateMachine stateMachine;

    @RequestMapping("/states")
    @ResponseBody
    public List<String> getStates() throws Exception {
        return  states.getStates();
    }

    @RequestMapping("/events")
    @ResponseBody
    public List<String> getEvents() throws Exception {
        return  events.getEvents();
    }
    @RequestMapping("/transtions")
    @ResponseBody
    public List<Map<String, String>> getTranstions() throws Exception {
        return  transition.getTransition();
    }
    @RequestMapping("/event")
    @ResponseBody
    public boolean sendEvent(@RequestParam(value = "event", required = true) String event) throws Exception {
        System.out.println("当前接收到入参：" + event);
        return  stateMachine.sendEvent(event);
    }
}
