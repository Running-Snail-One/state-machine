package com.demo.controller;

import com.alibaba.nacos.api.exception.NacosException;
import com.demo.model.rq.NacosConfigUpdateRQ;
import com.demo.service.NacosOperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Nacos api文档")
public class NacosConfigController {
    @Autowired
    private NacosOperationService nacosOperationService;

    @ApiOperation(value = "加载nacos配置")
    @RequestMapping(value = "/getConfig", method = RequestMethod.GET)
    public String getConfig() throws NacosException {
        return nacosOperationService.getConfig();
    }

    @ApiOperation(value = "新增nacos配置")
    @PostMapping(value = "/insertConfig")
    @ApiImplicitParams({@ApiImplicitParam(name = "flag", value = "flag：0：添加状态；1：添加事件；2：添加流转状志", dataType = "String", required = true, defaultValue = "")})
    public Boolean insertConfig(@RequestParam String flag, @RequestBody NacosConfigUpdateRQ nacosConfigUpdateRQ) throws NacosException {
        return nacosOperationService.insertNacosConfig(flag, nacosConfigUpdateRQ);
    }

    @ApiOperation(value = "删除nacos配置")
    @PostMapping(value = "/delConfig")
    @ApiImplicitParams({@ApiImplicitParam(name = "flag", value = "flag：0：删除状态；1：删除事件；2：删除流转状志", dataType = "String", required = true, defaultValue = "")})
    public Boolean delConfig(@RequestParam String flag, @RequestBody NacosConfigUpdateRQ nacosConfigUpdateRQ) throws NacosException {
        return  nacosOperationService.deleteNacosConfig(flag, nacosConfigUpdateRQ);
    }


}
