package com.demo.mongo;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.ObjectStateMachineFactory;
import org.springframework.statemachine.config.model.*;

import java.util.ArrayList;
import java.util.Collection;

public class SsmConfigStorage {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println("第" + i+ "次产生状态机实例ID： " + getSSM());
        }

    }
    public static String getSSM(){
        // setup configuration data
        ConfigurationData<String, String> configurationData = new ConfigurationData<>();

        // setup states data
        Collection<StateData<String, String>> stateData = new ArrayList<>();
        stateData.add(new StateData<String, String>("S1", true));
        stateData.add(new StateData<String, String>("S2"));
        StatesData<String, String> statesData = new StatesData<>(stateData);

        // setup transitions data
        Collection<TransitionData<String, String>> transitionData = new ArrayList<>();
        transitionData.add(new TransitionData<String, String>("S1", "S2", "E1"));
        TransitionsData<String, String> transitionsData = new TransitionsData<>(transitionData);

        // setup model
        StateMachineModel<String, String> stateMachineModel = new DefaultStateMachineModel<>(configurationData, statesData,
                transitionsData);

        // instantiate machine via factory
        ObjectStateMachineFactory<String, String> factory = new ObjectStateMachineFactory<>(stateMachineModel);
        StateMachine<String, String> stateMachine = factory.getStateMachine();
        return stateMachine.getUuid().toString().replace("-","");
    }
}

