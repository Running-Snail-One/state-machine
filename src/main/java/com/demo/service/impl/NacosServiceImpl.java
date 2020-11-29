package com.demo.service.impl;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.demo.config.NacosConfig;
import com.demo.model.ConfigEntity;
import com.demo.service.NacosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NacosServiceImpl implements NacosService {

    @NacosInjected
    private ConfigService configService;
    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;
    @Autowired
    private NacosConfig nacosConfig;

    @Override
    public String getConfig() throws NacosException {
        return configService.getConfig(nacosConfig.getDataId(),nacosConfig.getGroup(),50);
    }

    @Override
    public boolean insertNacosConfig(String str) throws NacosException {
        boolean result = false;
        try {
             result = configService.publishConfig(nacosConfig.getDataId(),nacosConfig.getGroup(), str);
        } catch (NacosException e) {
            //自定义错误异常信息
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteNacosConfig(ConfigEntity configEntity) {
        return false;
    }

    @Override
    public boolean insertState(String str) {
        return false;
    }

    @Override
    public boolean deleteState(String str) {
        return false;
    }

    @Override
    public boolean insertEvent(String str) {
        return false;
    }

    @Override
    public boolean deleteEvent(String str) {
        return false;
    }
}
