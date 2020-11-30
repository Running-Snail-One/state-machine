package com.demo.service.impl;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.demo.config.NacosConfig;
import com.demo.constant.NacosConstants;
import com.demo.model.ConfigEntity;
import com.demo.model.rq.NacosConfigUpdateRQ;
import com.demo.service.NacosOperationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.Configuration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    @Override
    public boolean insertNacosConfig(String flag, NacosConfigUpdateRQ nacosConfigUpdateRQ) throws NacosException {
        //获取配置中心配置
        String config = nacosOperationService.getConfig();
        switch (flag) {
            case "0":
                config = addStateConfig(config, nacosConfigUpdateRQ.getState());
                break;
            case "1":
                config = addEventConfig(config, nacosConfigUpdateRQ.getEvent());
                break;
            case "2":
                config = addTranstion(config, nacosConfigUpdateRQ.getTransition());
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

    public boolean publishConfig(String content) {
        boolean publishResult = false;
        try {
            //发布插入的配置
            publishResult = configService.publishConfig(nacosConfig.getDataId(), nacosConfig.getGroup(), content);
        } catch (NacosException e) {
            //自定义错误异常信息
            e.printStackTrace();
        }
        return publishResult;
    }

    @Override
    public boolean updateNacosConfig(String flage, NacosConfigUpdateRQ nacosConfigUpdateRQ) throws NacosException {
        return false;
    }

    @Override
    public boolean deleteNacosConfig(String flag, NacosConfigUpdateRQ nacosConfigUpdateRQ) throws NacosException {
        //获取配置中心配置
        String config = nacosOperationService.getConfig();
        switch (flag) {
            case "0":
                //清除配置中心对应配置
                config = delStateConfig(config, nacosConfigUpdateRQ.getState());
                //重新发布
                return publishConfig(config);
            case "1":
                config = delEventConfig(config, nacosConfigUpdateRQ.getEvent());
                return publishConfig(config);
            case "2":
                config = delTransitionConfig(config, nacosConfigUpdateRQ.getTransition());
                return publishConfig(config);
        }
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
                        + NacosConstants.FOUR_SPACE_ONE_LINE + param + NacosConstants.R;
                break;
            }
        }
        return StringUtils.join(split, NacosConstants.RETURN);
    }

    /**
     * @desc: 向配置中心添加事件配置参数
     * @author: 范帅兵
     * @date: 2020/11/30
     * @param:
     **/
    public String addEventConfig(String config, String param) {
        String[] split = config.split("\n");
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains(NacosConstants._EVENTS)) {
                split[i] = split[i] + NacosConstants.RETURNSEPARATOR
                        + NacosConstants.FOUR_SPACE_ONE_LINE + param + NacosConstants.R;
                break;
            }
        }
        return StringUtils.join(split, NacosConstants.RETURN);
    }

    /**
     * @desc: 向配置中心添加流转状态配置参数
     * @author: 范帅兵
     * @date: 2020/11/30
     **/
    public String addTranstion(String config, List<ConfigEntity> param) {
        String[] split = config.split("\n");
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains(NacosConstants._TRANSITION)) {
                split[i] += NacosConstants.RETURNSEPARATOR;
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < param.size(); j++) {
                    sb.append(NacosConstants.TWO_SPACE_ONE_LINE + NacosConstants.TRANSTION_SOURCE)
                            .append(param.get(j).getSource())
                            .append(NacosConstants.RETURN)
                            .append(NacosConstants.FOUR_SPACE + NacosConstants.TRANSTION_TARGET)
                            .append(param.get(j).getTarget())
                            .append(NacosConstants.RETURN)
                            .append(NacosConstants.FOUR_SPACE + NacosConstants.TRANSTION_EVENT)
                            .append(param.get(j).getEvent());
                }
                split[i] += sb.toString();
                break;
            }
        }
        return StringUtils.join(split, NacosConstants.RETURN);
    }

    /**
     * @param config 配置中心读取到的值
     * @param param  要删除的状态值
     * @return 删除后的配置
     * @desc: 删除指定定状态的配置参数
     */
    public String delStateConfig(String config, String param) {
        String[] split = config.split("\n");
        List<String> list = Arrays.asList(split);
        List<String> arrList = new ArrayList<String>(list);
        arrList.remove(NacosConstants.FOUR_SPACE_ONE_LINE + param + NacosConstants.R);
        //清除流转状态包含该状态的集合
        List<String> strings = delStateAndEventRelationTransitionConfig(arrList, param);
        //重新组装配置中心内容
        return StringUtils.join(strings, "\n");
    }

    /**
     * @param config 配置中心读取到的值
     * @param param  要删除的事件值
     * @return 删除后的配置
     * @desc: 删除指定定事件的配置参数
     */
    public String delEventConfig(String config, String param) {
        String[] split = config.split("\n");
        List<String> list = Arrays.asList(split);
        List<String> arrList = new ArrayList<String>(list);
        arrList.remove(NacosConstants.FOUR_SPACE_ONE_LINE + param + NacosConstants.R);
        //清除流转状态包含该事件的集合
        List<String> strings = delStateAndEventRelationTransitionConfig(arrList, param);
        //重新组装配置中心内容
        return StringUtils.join(arrList, "\n");
    }

    /**
     * @param config         配置中心读取到的值
     * @param configEntities 要删除的流转状态值
     * @return 删除后的配置
     * @desc: 删除指定流转状态的配置参数
     */
    public String delTransitionConfig(String config, List<ConfigEntity> configEntities) {
        String[] split = config.split("\n");
        List<String> list = Arrays.asList(split);
        List<String> configList = new ArrayList<String>(list);
        //找到配置中心"_transition:"关键字所在的行位置
        int i = configList.indexOf(NacosConstants.TWO_SPACE + NacosConstants._TRANSITION + NacosConstants.R);

        for (ConfigEntity configEntity : configEntities) {
            String source = configEntity.getSource();
            String target = configEntity.getTarget();
            for (int j = i + 1; j < configList.size() - 1; j++) {
                if (!configList.get(j).contains(NacosConstants.TRANSTION_SOURCE) && !configList.get(j).contains(NacosConstants.TRANSTION_TARGET)) {
                    break;
                } else if (configList.get(j).contains(source) && configList.get(j + 1).contains(target)) {
                    //移除该组数据
                    configList.remove(j);
                    configList.remove(j);
                    configList.remove(j);
                    j = j - 1;
                } else {
                    j += 2;
                }
            }
        }
        return StringUtils.join(configList, "\n");
    }

    /**
     * @param config 配置中心读取到的值
     * @param param  要删除的配置
     * @return 删除后的配置
     * @desc: 删除与给定状态或事件相关联的流转状态值
     */
    public List<String> delStateAndEventRelationTransitionConfig(List<String> config, String param) {
        //找到配置中心"_transition:"关键字所在的行位置
        int i = config.indexOf(NacosConstants.TWO_SPACE + NacosConstants._TRANSITION + NacosConstants.R);
        for (int j = i + 1; j < config.size() - 1; j++) {
            if (!config.get(j).contains(NacosConstants.TRANSTION_SOURCE) && !config.get(j).contains(NacosConstants.TRANSTION_TARGET)) {
                break;
            } else if (config.get(j).contains(param) || config.get(j + 1).contains(param)) {
                //移除一组数据
                config.remove(j);
                config.remove(j);
                config.remove(j);
                j = j - 1;
            } else {
                j += 2;
            }
        }
        return config;
    }

}
