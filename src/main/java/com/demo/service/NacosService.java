package com.demo.service;

import com.alibaba.nacos.api.exception.NacosException;
import com.demo.model.ConfigEntity;

/**
 * Nacos api
 */
public interface NacosService {
                 /**针对流转状态的更新*/

    /**
     *
     */

    public String getConfig() throws NacosException;
    /**
     * 新增Nacos配置
     */
    public boolean insertNacosConfig(String configEntity) throws NacosException;

    /**
     * 删除Nacos配置
     */
    public boolean deleteNacosConfig(ConfigEntity configEntity);
                 /**针对全量状态的更新*/
    /**
     * 添加状态
     */
    public boolean insertState(String str);

    /**
     * 删除状态
     */
    public boolean deleteState(String str);
                /**针对全量事件的更新*/

    /**
     * 添加事件
      */
    public boolean insertEvent(String str);

    /**
     * 删除事件
     */
    public boolean deleteEvent(String str);
}
