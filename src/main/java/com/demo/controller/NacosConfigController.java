package com.demo.controller;

import com.alibaba.nacos.api.exception.NacosException;
import com.demo.service.NacosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@Api(tags = "Nacos api文档")
public class NacosConfigController {
    @Autowired
    private NacosService nacosService;

    @ApiOperation(value = "新增nacos配置")
    @RequestMapping(value = "/pushConfig",method = RequestMethod.GET)
    @ApiImplicitParams(@ApiImplicitParam(name = "content",value = "配置信息"
            ,dataType = "String",required = true,defaultValue = "test"))
    public Boolean publish(@RequestParam String content) throws NacosException {
        String config = nacosService.getConfig();
        String[] split = config.split("\n");
        for (int i = 0; i < split.length; i++) {
            if(split[i].contains("states")){
                split[i] = split[i] + "\n" + "    - " + content;
                break;
            }
        }
        boolean b = nacosService.insertNacosConfig(StringUtils.join(split,"\n"));
        return b;
    }


    @ApiOperation(value = "加载nacos配置")
    @RequestMapping(value = "/getConfig",method = RequestMethod.GET)
    @ApiImplicitParams(@ApiImplicitParam(name = "content",value = "配置信息"
            ,dataType = "String",required = true,defaultValue = "getTest"))
    public String getConfig() throws NacosException {
        return nacosService.getConfig();
    }
}
