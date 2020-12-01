package com.demo.service;

import com.alibaba.nacos.api.exception.NacosException;
import com.demo.model.ConfigEntity;

import java.util.List;

/**
 * Nacos api
 */
public interface NacosOperationService {

    /**
     * 获取配置中心所有配置
     */
    public String getConfig() throws NacosException;

    /**
     * 新增状态配置
     */
    public boolean insertStateConfig(String state) throws NacosException;
    /**
     * 新增事件配置
     */
    public boolean insertEventConfig(String event) throws NacosException;
    /**
     * 新增流转状态配置
     */
    public boolean insertTransitionConfig(List<ConfigEntity> configEntities) throws NacosException;
    /**
     * 删除状态配置
     */
    public boolean delStateConfig(String state) throws NacosException;
    /**
     * 删除事件配置
     */
    public boolean delEventConfig(String event) throws NacosException;
    /**
     * 删除流转状态配置
     */
    public boolean delTransitionConfig(List<ConfigEntity> configEntities) throws NacosException;

    /**
     * 更新流转状态
     */
    public boolean updateTransition(List<ConfigEntity> oldTransition, List<ConfigEntity> newTransition) throws NacosException;

    /**
     * 更新状态
     */
    public boolean updateStates(String oldState, String newState) throws NacosException;

    /**
     * 更新事件
     */
    public boolean updateEvents(String oldEvent, String newEvent) throws NacosException;
}
