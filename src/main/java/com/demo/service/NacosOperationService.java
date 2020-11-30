package com.demo.service;

import com.alibaba.nacos.api.exception.NacosException;
import com.demo.model.ConfigEntity;
import com.demo.model.rq.NacosConfigUpdateRQ;

/**
 * Nacos api
 */
public interface NacosOperationService {
                 /**针对流转状态的更新*/

    /**
     *获取配置中心所有配置
     */
    public String getConfig() throws NacosException;

    /**
     * 新增Nacos配置
     */
    public boolean insertNacosConfig(String flage, NacosConfigUpdateRQ nacosConfigUpdateRQ) throws NacosException;

    /**
     * 更新Nacos配置
     */
    public boolean updateNacosConfig(String flage, NacosConfigUpdateRQ nacosConfigUpdateRQ) throws NacosException;
    /**
     * 删除Nacos配置
     */
    public boolean deleteNacosConfig(String flag,NacosConfigUpdateRQ nacosConfigUpdateRQ) throws NacosException;
}
