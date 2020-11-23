package com.demo;

import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 状态持久化
 */
@Component
public class BizStateMachinePersist implements StateMachinePersist<NsiteStates, NsiteEvents, Integer> {

    //使用的是HashMap作为模拟存储介质，正式项目中需要使用真实的状态获取途径
    static Map<Integer, NsiteStates> cache = new HashMap<>(16);

    /**
     * 更新状态机
     * @param stateMachineContext
     * @param integer
     * @throws Exception
     */
    @Override
    public void write(StateMachineContext<NsiteStates, NsiteEvents> stateMachineContext, Integer integer) throws Exception {
        cache.put(integer, stateMachineContext.getState());
    }

    /**
     * 读取状态机
     * @param integer
     * @return
     * @throws Exception
     */
    @Override
    public StateMachineContext<NsiteStates, NsiteEvents> read(Integer integer) throws Exception {
        // 注意状态机的初识状态与配置中定义的一致
        return cache.containsKey(integer) ?
                new DefaultStateMachineContext<>(cache.get(integer), null, null, null, null, "turnstileStateMachine") :
                new DefaultStateMachineContext<>(NsiteStates.LOCKED, null, null, null, null, "turnstileStateMachine");
    }
}

