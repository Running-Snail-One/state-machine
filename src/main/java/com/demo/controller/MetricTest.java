package com.demo.controller;

import com.demo.config.StateMachineMake;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Api(tags = "jemeter性能测试")
public class MetricTest {

     @Autowired
     private StateMachineMake stateMachineMake;

    @Autowired
    @Qualifier("orderRedisPersister")
    private StateMachinePersister<String, String, String> stateMachinePersister;

    List<StateMachine<String,String>> totalMemory = new ArrayList<>();

    @ApiOperation(value = "状态机10W次创建性能测试")
    @RequestMapping(value = "/createMachineJemeterTest",method = RequestMethod.GET)
    public void creatStatusMachine() throws Exception {
        int sum = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            StateMachine<String, String> stateMachine = stateMachineMake.getStateMachine();
            System.out.println("第" + i + "个状态机创建成功" + ", size: "+ stateMachine.toString().getBytes().length);
        }
        long end = System.currentTimeMillis();
        System.out.println("创建10W个状态机耗时: "+ (end-start));
        System.out.println("创建10W个状态机消耗内存: " + sum/1024/1024 + "M");
    }
    @ApiOperation(value = "状态机10W次序列化测试")
    @RequestMapping(value = "/serialTestTest",method = RequestMethod.GET)
    public void serialTest() throws Exception {
        StateMachine<String, String> stateMachine = stateMachineMake.stateMachine();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            stateMachinePersister.persist(stateMachine, UUID.randomUUID().toString());
            System.out.println("第" + i + "个状态机序列化创建成功");
        }
        long end = System.currentTimeMillis();
        System.out.println("序列化10W个状态机耗时: "+ (end-start));
    }
}
