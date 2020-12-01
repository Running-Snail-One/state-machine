package com.demo.controller;

import com.alibaba.nacos.api.exception.NacosException;
import com.demo.constant.ResultEnum;
import com.demo.exception.StateMachineException;
import com.demo.model.ConfigEntity;
import com.demo.model.rq.NacosTransitionConfigUpdateRQ;
import com.demo.service.NacosOperationService;
import com.demo.utils.BeanUtil;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "Nacos api文档")
public class NacosConfigController {
    @Autowired
    private NacosOperationService nacosOperationService;

    @ApiOperation(value = "加载配置-测试使用")
    @RequestMapping(value = "/getConfig", method = RequestMethod.GET)
    public String getConfig() throws NacosException {
        return nacosOperationService.getConfig();
    }

    @ApiOperation(value = "新增状态")
    @PostMapping(value = "/insertState")
    @ApiImplicitParams({@ApiImplicitParam(name = "state", value = "状态机状态", dataType = "String", required = true, defaultValue = "")})
    public Boolean insertState(@RequestParam  String state) throws NacosException {
        return nacosOperationService.insertStateConfig(state);
    }

    @ApiOperation(value = "新增事件")
    @PostMapping(value = "/insertEvent")
    @ApiImplicitParams({@ApiImplicitParam(name = "event", value = "状态机事件", dataType = "String", required = true, defaultValue = "")})
    public Boolean insertEvent(@RequestParam String event) throws NacosException {
        return nacosOperationService.insertEventConfig(event);
    }

    @ApiOperation(value = "新增流转状态")
    @PostMapping(value = "/insertTransition")
    public Boolean insertTransition(@RequestBody @ApiParam(name = "configEntityList",value = "流转状态",required = true)
                                                 List<ConfigEntity> configEntityList) throws NacosException {
        return nacosOperationService.insertTransitionConfig(configEntityList);
    }

    @ApiOperation(value = "删除状态配置")
    @PostMapping(value = "/delState")
    @ApiImplicitParams({@ApiImplicitParam(name = "state", value = "状态", dataType = "String", required = true, defaultValue = "")})
    public Boolean delState(@RequestParam String state) throws NacosException {
        return nacosOperationService.delStateConfig(state);
    }

    @ApiOperation(value = "删除事件配置")
    @PostMapping(value = "/delEvent")
    @ApiImplicitParams({@ApiImplicitParam(name = "event", value = "事件", dataType = "String", required = true, defaultValue = "")})
    public Boolean delEvent(@RequestParam String event) throws NacosException {
        return nacosOperationService.delEventConfig(event);
    }

    @ApiOperation(value = "删除流转状态配置")
    @PostMapping(value = "/delTransition")
    public Boolean delTransition(@RequestBody @ApiParam(name = "待删除流转状态表",required = true) List<ConfigEntity> configEntityList) throws NacosException {
        return nacosOperationService.delTransitionConfig(configEntityList);
    }

//    @ApiOperation(value = "更新状态配置")
//    @PostMapping(value = "/updateState")
//    @ApiImplicitParams({@ApiImplicitParam(name = "originState", value = "源状态", dataType = "String", required = true, defaultValue = "")
//            , @ApiImplicitParam(name = "oldState", value = "目标状态", dataType = "String", required = true, defaultValue = "")})
//    public Boolean updateState(@RequestParam String originState, @RequestParam String oldState) throws NacosException {
//        return nacosOperationService.updateStates(originState, oldState);
//    }
//
//    @ApiOperation(value = "更新事件配置")
//    @PostMapping(value = "/updateEvent")
//    @ApiImplicitParams({@ApiImplicitParam(name = "originEvent", value = "源事件", dataType = "String", required = true, defaultValue = "")
//            , @ApiImplicitParam(name = "newEvent", value = "目标事件", dataType = "String", required = true, defaultValue = "")})
//    public Boolean updateEvent(@RequestParam String originEvent, @RequestParam String newEvent) throws NacosException {
//        return nacosOperationService.updateEvents(originEvent, newEvent);
//    }

    @ApiOperation(value = "更新流转状态配置")
    @PostMapping(value = "/updateTransition")
    public Boolean updateTransition(@Valid @RequestBody @ApiParam(name = "nacosTransitionConfigUpdateRQ",value = "流转状态更新参数",required = true)
                                                NacosTransitionConfigUpdateRQ nacosTransitionConfigUpdateRQ, BindingResult bindingResult) throws NacosException {
        if(bindingResult.hasErrors()){
            throw new StateMachineException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        List<ConfigEntity> originList = BeanUtil.TransitionRQ2ConfigEntity(nacosTransitionConfigUpdateRQ.getOriginConfigEntityLists());
        List<ConfigEntity> targetList = BeanUtil.TransitionRQ2ConfigEntity(nacosTransitionConfigUpdateRQ.getTargetConfigEntityLists());
        return nacosOperationService.updateTransition(originList, targetList);
    }
}
