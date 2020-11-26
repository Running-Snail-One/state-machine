package com.demo.dao;

import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 状态机本地内存持久化
 */
@Component
public class InMemoryStateMachinePersist implements StateMachinePersist {

    private Map<String, StateMachineContext<String, String>> map = new HashMap<String, StateMachineContext<String,String>>();
    @Override
    public void write(StateMachineContext context, Object contextObj) throws Exception {
        map.put((String) contextObj, context);
    }

    @Override
    public StateMachineContext read(Object contextObj) throws Exception {
        return map.get(contextObj);
    }
}
