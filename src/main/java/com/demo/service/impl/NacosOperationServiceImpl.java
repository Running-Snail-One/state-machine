package com.demo.service.impl;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.demo.config.NacosConfig;
import com.demo.constant.NacosConstants;
import com.demo.service.NacosOperationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NacosOperationServiceImpl implements NacosOperationService {

    @NacosInjected
    private ConfigService configService;
    @Autowired
    private NacosConfig nacosConfig;
    @Autowired
    private NacosOperationService nacosOperationService;

    @Override
    public String getConfig() throws NacosException {
        return configService.getConfig(nacosConfig.getDataId(), nacosConfig.getGroup(), 50);
    }

    /**
     * @desc: 添加配置中心参数
     * @author:
     * @date: 2020/11/30
     * @param: flag:"0":添加状态；"1"：添加事件；"2"：添加流转状
     **/
    @Override
    public boolean insertNacosConfig(String flag, String addConfig) throws NacosException {
        //获取配置中心配置
        String config = nacosOperationService.getConfig();
        switch (flag) {
            case "0":
                config = addStateConfig(config, addConfig);
                break;
            case "1":
                config = addEventConfig(config, addConfig);
                break;
            case "2":
                config = addTranstion(config, addConfig);
                break;
        }
        boolean result = false;
        try {
            //发布插入的配置
            result = configService.publishConfig(nacosConfig.getDataId(), nacosConfig.getGroup(), config);
        } catch (NacosException e) {
            //自定义错误异常信息
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteNacosConfig(String flag, String str) {
        return false;
    }

    /**
     * @desc: 向配置中心添加状态配置参数
     * @author: 范帅兵
     * @date: 2020/11/30
     * @param:
     **/
    public String addStateConfig(String config, String param) {
        String[] split = config.split("\n");
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains(NacosConstants._STATES)) {
                split[i] = split[i] + NacosConstants.RETURNSEPARATOR
                        + NacosConstants.SPACEANDLINE + param;
                break;
            }
        }
        return StringUtils.join(split, NacosConstants.RETURN);
    }

    /**
     * @desc: 向配置中心添加状态配置参数
     * @author: 范帅兵
     * @date: 2020/11/30
     * @param:
     **/
    public String addEventConfig(String config, String param) {
        String[] split = config.split("\n");
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains(NacosConstants._EVENTS)) {
                split[i] = split[i] + NacosConstants.RETURNSEPARATOR
                        + NacosConstants.SPACEANDLINE + param;
                break;
            }
        }
        return StringUtils.join(split, NacosConstants.RETURN);
    }

    /**
     * @desc: 向配置中心添加状态配置参数
     * @author: 范帅兵
     * @date: 2020/11/30
     * @param:
     **/
    public String addTranstion(String config, String param) {
        String[] split = config.split("\n");
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains(NacosConstants._EVENTS)) {
                split[i] = split[i] + NacosConstants.RETURNSEPARATOR
                        + NacosConstants.SPACEANDLINE + param;
                break;
            }
        }
        return StringUtils.join(split, NacosConstants.RETURN);
    }
}
