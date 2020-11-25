package com.demo.controller;

import com.demo.dao.RedisStateMachineRepository;
import com.demo.model.NsiteEvents;
import com.demo.model.NsiteStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 状态机controller层构建
 * @author: 范帅兵
 * @create: 2020-11-24 23:00
 **/
@Controller
public class StateMachineController {
    @Autowired
    private StateMachine<NsiteStates, NsiteEvents> stateMachine;

    @Autowired
    private StateMachinePersister<NsiteStates, NsiteEvents, String> stateMachinePersister;

    @Autowired
    private RedisStateMachineRepository redisStateMachineRepository;

    @RequestMapping("/state")
    @ResponseBody
    public String feedAndGetState(@RequestParam(value = "user", required = false) String user,
                                  @RequestParam(value = "id", required = false) NsiteEvents id, Model model) throws Exception {
        model.addAttribute("user", user);
        model.addAttribute("allTypes", NsiteEvents.values());
        model.addAttribute("stateChartModel", "stateChartModel");
        // we may get into this page without a user so
        // do nothing with a state machine
        if (StringUtils.hasText(user)) {
            redisStateMachineRepository.resetStateMachineFromStore(user);
            if (id != null) {
                redisStateMachineRepository.feedMachine(user, id);
            }
            model.addAttribute("states", stateMachine.getState().getIds());
            model.addAttribute("extendedState", stateMachine.getExtendedState().getVariables());
        }
        return "states";
    }

}
