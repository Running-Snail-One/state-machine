package com.demo.config;

import com.alibaba.fastjson.JSONObject;
import com.demo.model.ConfigEntity;
import com.demo.model.Transition;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.config.model.*;
import org.springframework.statemachine.state.PseudoStateKind;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SSMConfig {

    @Autowired
    private static Transition transition;

    private static final HashSet<String> states = new HashSet<String>();
    private static final HashSet<ConfigEntity> configEntities = new HashSet<ConfigEntity>();

    public static final StateData<String, String> initState = new StateData<String, String>("HOME" ,true);
    public static final StateData<String, String> endState = new StateData<String, String>("结束状态");

    public static HashSet <String> getStates() {
        return states;
    }

    public static HashSet <ConfigEntity> getConfigEntities() {
        return configEntities;
    }

    /**
     * 配置的构造方法
     */
    static {
        //构造配置信息列表，这个可以根据业务实际需求设置，可自定义
        System.out.println(transition.getTransition());
        Set<ConfigEntity> configEntities = new HashSet <ConfigEntity>( Arrays.asList(new ConfigEntity("初始状态","状态1","事件１"),
                new ConfigEntity("状态１","状态２","事件２"),
                new ConfigEntity("状态２","状态1","事件３")));
        for(ConfigEntity configEntity : configEntities){
            states.add(configEntity.getSource());
            configEntities.add(configEntity);
        }
    }

    /**
     * map集合转list
     * @param lists
     * @return
     */
    public static List map2List(List<Map<String, String>> lists){
        ArrayList<Object> arr = new ArrayList<>();
        ConfigEntity configEntity = null;
        for (int i = 0; i < lists.size(); i++) {
            BeanUtils.copyProperties(lists.get(i),ConfigEntity.class);
            arr.add(configEntity);
        }
        return arr;
    }
    /**
     * 构建　ConfigurationData，在这一步也可以构建为分布式的，如基于zookeeper
     * @return
     */
    public static ConfigurationData<String,String> getConfigurationData(){
        ConfigurationData<String, String> configurationData = new ConfigurationData<String, String>();
        return configurationData;
    }

    /**
     * 构建状态数据信息对象, 这一步是构建状态机的各个状态字段，用于装载状态机的状态转换之间的状态配置
     * @return
     */
    public static StatesData<String,String> getStatesData(){
        HashSet<StateData<String, String>> stateDatas = new HashSet<StateData<String, String>>();
        //初始状态
        initState.setPseudoStateKind(PseudoStateKind.INITIAL);
        stateDatas.add(initState);

        //结束状态
        endState.setEnd(true);
        endState.setPseudoStateKind(PseudoStateKind.END);
        stateDatas.add(endState);

        //其他状态加载
        for (String state: states){
            StateData<String, String> stateData = new StateData<String, String>(state);
            stateDatas.add(stateData);
        }

        //构建
        StatesData<String, String> statesData = new StatesData<String, String>(stateDatas);

        return statesData;
    }

    /**
     * 状态事物转换的流程配置
     * @return
     */
    public static TransitionsData<String,String> getTransitionsData(){
        HashSet<TransitionData<String,String>> transitionDatas = new HashSet<TransitionData<String,String>>();
        for (ConfigEntity configEntity : configEntities ){

            TransitionData<String,String> transitionData = new TransitionData<String,String>(configEntity.getSource(),
                    configEntity.getTarget(),
                    configEntity.getEvent()
            );
            transitionDatas.add(transitionData);

        }
        TransitionsData<String,String> transitionsData = new TransitionsData<String,String>(transitionDatas);

        return transitionsData;
    }

}
