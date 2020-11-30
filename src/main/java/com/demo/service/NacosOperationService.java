package com.demo.service;

import com.alibaba.nacos.api.exception.NacosException;
import com.demo.model.ConfigEntity;

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
    public boolean insertNacosConfig(String flage,String str) throws NacosException;

    /**
     * 删除Nacos配置
     */
    public boolean deleteNacosConfig(String flag, String str);
}
