package com.demo;


import com.demo.constant.NsiteEvents;
import com.demo.constant.NsiteStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;

@SpringBootApplication()
public class Application implements CommandLineRunner {

    @Autowired
    private StateMachine<NsiteStates, NsiteEvents> stateMachine;

    @Autowired
    private StateMachinePersister<NsiteStates, NsiteEvents, String> stateMachinePersister;

//    @Autowired
//    private StateMachinePersister stateMachinePersist;
//    @Autowired
//    private StateMachineHandler stateMachineHandler;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        stateMachine.start();
//        sendTest();
        persist();
//        resetStateMachineFromStore("admin");
        stateMachine.stop();
    }

    public void sendTest(){

        stateMachine.sendEvent(NsiteEvents.EVENT_1);
        stateMachine.sendEvent(NsiteEvents.EVENT_2);
        stateMachine.sendEvent(NsiteEvents.EVENT_3);
    }

    /**
     * 状态持久化
     * @throws Exception
     */
    public void persist() throws Exception {
        stateMachine.sendEvent(NsiteEvents.EVENT_1);
        stateMachinePersister.persist(stateMachine, "testprefix:" + "admin");
    }

    /**
     * 回复状态
     * @param user
     * @return
     * @throws Exception
     */
    private StateMachine<NsiteStates, NsiteEvents> resetStateMachineFromStore(String user) throws Exception {
        return stateMachinePersister.restore(stateMachine, "testprefix:" + user);
    }

}
