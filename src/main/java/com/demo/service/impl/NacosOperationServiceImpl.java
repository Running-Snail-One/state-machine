package com.demo.service.impl;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.demo.config.EventsConfig;
import com.demo.config.NacosConfig;
import com.demo.config.StatesConfig;
import com.demo.config.TransitionConfig;
import com.demo.constant.NacosConstants;
import com.demo.constant.ResultEnum;
import com.demo.exception.StateMachineException;
import com.demo.model.ConfigEntity;
import com.demo.service.NacosOperationService;
import com.demo.utils.JavaBeanUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class NacosOperationServiceImpl implements NacosOperationService {

    @Resource(name = "stateMachineConfigService")
    private ConfigService configService;
    @Autowired
    private NacosConfig nacosConfig;
    @Autowired
    private NacosOperationService nacosOperationService;
    @Autowired
    private EventsConfig eventsConfig;
    @Autowired
    private StatesConfig statesConfig;
    @Autowired
    private TransitionConfig transitionConfig;

    @Override
    public String getConfig() throws NacosException {
        return configService.getConfig(nacosConfig.getDataId(), nacosConfig.getGroup(), 500);
    }

    @Override
    public boolean insertStateConfig(String state) throws NacosException {
        boolean exist = statesConfig.getStates().contains(state);
        if (exist)
            throw new StateMachineException(ResultEnum.CONFIG_CENTER_EXIST_THIS_STATE);
        //获取配置中心配置
        String config = nacosOperationService.getConfig();
        //获取更新后的配置
        String resultConfig = addStateConfig(config, state);
        //配置中心发布
        return publishConfig(resultConfig);
    }

    @Override
    public boolean insertEventConfig(String event) throws NacosException {
        //校验配置中心是否存在该事件
        boolean exist = eventsConfig.getEvents().contains(event);
        if (exist)
            throw new StateMachineException(ResultEnum.CONFIG_CENTER_EXIST_THIS_EVENT);
        //获取配置中心配置
        String config = nacosOperationService.getConfig();
        //获取更新后的配置
        String resultConfig = addEventConfig(config, event);
        //配置中心发布
        return publishConfig(resultConfig);
    }

    @Override
    public boolean insertTransitionConfig(List<ConfigEntity> configEntities) throws Exception {
        //校验配置中心是否包含该流转状态
        List<Map<String, String>> transitionMap = transitionConfig.getTransition();
        boolean retain = JavaBeanUtil.retain(transitionMap, configEntities);
        if (retain)
            throw new StateMachineException(ResultEnum.CONFIG_CENTER_EXIST_THIS_TRANSITION);
        //获取配置中心配置
        String config = nacosOperationService.getConfig();
        //获取更新后的配置
        String resultConfig = addTranstion(config, configEntities);
        //配置中心发布
        return publishConfig(resultConfig);
    }

    @Override
    public boolean delStateConfig(String state) throws NacosException {
        //校验配置中心是否存在该状态
        boolean exist = statesConfig.getStates().contains(state);
        if (!exist) {
            throw new StateMachineException(ResultEnum.CONFIG_CENTER_NO_THIS_STATE);
        }
        //获取配置中心配置
        String config = nacosOperationService.getConfig();
        //获取更新后的配置
        String resulConfig = delStateConfig(config, state);
        //配置中心发布
        return publishConfig(resulConfig);
    }

    @Override
    public boolean delEventConfig(String event) throws NacosException {
        //校验配置中心是否存在该事件
        boolean exist = eventsConfig.getEvents().contains(event);
        if (!exist) {
            throw new StateMachineException(ResultEnum.CONFIG_CENTER_NO_THIS_EVENT);
        }
        //获取配置中心配置
        String config = nacosOperationService.getConfig();
        //获取更新后的配置
        String resulConfig = delEventConfig(config, event);
        //配置中心发布
        return publishConfig(resulConfig);
    }

    @Override
    public boolean delTransitionConfig(List<ConfigEntity> configEntities) throws NacosException {
        //获取配置中心配置
        String config = nacosOperationService.getConfig();
        //获取更新后的配置
        String resulConfig = delTransitionConfig(config, configEntities);
        //配置中心发布
        return publishConfig(resulConfig);
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
                        + NacosConstants.FOUR_SPACE_ONE_LINE + param + slashR(config);
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
                        + NacosConstants.FOUR_SPACE_ONE_LINE + param + slashR(config);
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
        List<String> list = Arrays.asList(split);
        List<String> configList = new ArrayList<String>(list);
        String rString = slashR(configList);
        //找到配置中心"_transition:"关键字所在的行位置;
        int i = configList.indexOf(NacosConstants.TWO_SPACE + NacosConstants._TRANSITION + rString);
        if (i == -1)
            throw new StateMachineException(ResultEnum.CONFIG_CENTER_NO_TANSITION);

        split[i] += NacosConstants.RETURNSEPARATOR;
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < param.size(); j++) {
            sb.append(NacosConstants.TWO_SPACE_ONE_LINE + NacosConstants.TRANSITION_SOURCE)
                    .append(param.get(j).getSource())
                    .append(rString + NacosConstants.RETURN)
                    .append(NacosConstants.FOUR_SPACE + NacosConstants.TRANSITION_TARGET)
                    .append(param.get(j).getTarget())
                    .append(rString + NacosConstants.RETURN)
                    .append(NacosConstants.FOUR_SPACE + NacosConstants.TRANSITION_EVENT)
                    .append(param.get(j).getEvent())
                    .append(slashR(configList));
            if (j != param.size() - 1)
                sb.append(NacosConstants.RETURN);
        }
        split[i] += sb.toString();


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
        arrList.remove(NacosConstants.FOUR_SPACE_ONE_LINE + param + slashR(arrList));
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
        arrList.remove(NacosConstants.FOUR_SPACE_ONE_LINE + param + slashR(arrList));
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
        //找到配置中心"_transition:"关键字所在的行位置;
        int i = configList.indexOf(NacosConstants.TWO_SPACE + NacosConstants._TRANSITION + slashR(configList));
        if (i == -1)
            throw new StateMachineException(ResultEnum.CONFIG_CENTER_NO_TANSITION);

        for (ConfigEntity configEntity : configEntities) {
            String source = configEntity.getSource();
            String target = configEntity.getTarget();
            Boolean successFlag = false;
            for (int j = i + 1; j < configList.size() - 1; j++) {
                if (!configList.get(j).contains(NacosConstants.TRANSITION_SOURCE) && !configList.get(j).contains(NacosConstants.TRANSITION_TARGET)) {
                    break;
                } else if (configList.get(j).contains(source) && configList.get(j + 1).contains(target)) {
                    //移除该组数据
                    configList.remove(j);
                    configList.remove(j);
                    configList.remove(j);
                    //配置中心找到了相应的流转状态配置并删除成功
                    successFlag = true;
                    j = j - 1;
                } else {
                    j += 2;
                }
            }
            if (!successFlag) {
                throw new StateMachineException(ResultEnum.NO_FIND_TRANSITION);
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
        boolean Rflag = config.get(0).contains("\r");
        //找到配置中心"_transition:"关键字所在的行位置
        int i = config.indexOf(NacosConstants.TWO_SPACE + NacosConstants._TRANSITION + slashR(config));
        if (i == -1)
            throw new StateMachineException(ResultEnum.CONFIG_CENTER_NO_TANSITION);
        for (int j = i + 1; j < config.size() - 1; j++) {
            if (!config.get(j).contains(NacosConstants.TRANSITION_SOURCE)
                    && !config.get(j).contains(NacosConstants.TRANSITION_TARGET)) {
                break;
            } else if (config.get(j).contains(param) //j和j+1用来判断状态；j+2用来判断事件
                    || config.get(j + 1).contains(param) || config.get(j + 2).contains(param)) {
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

    /**
     * 更新配置中心状态
     *
     * @param oldState 待更新状态
     * @param newState 新状态
     * @return
     * @throws NacosException
     */
    @Override
    public boolean updateStates(String oldState, String newState) throws NacosException {
        if (StringUtils.isBlank(oldState))
            throw new StateMachineException(ResultEnum.OLD_STATE_NULL);
        if (StringUtils.isNotBlank(newState))
            throw new StateMachineException(ResultEnum.NEW_STATE_NULL);
        //获取配置中心配置
        String config = nacosOperationService.getConfig();
        //删除旧状态
        String configAfterDelOldState = delStateConfig(config, oldState);
        //添加新状态
        String configAfterAddNewState = addStateConfig(configAfterDelOldState, newState);
        //配置中心进行发布
        return publishConfig(configAfterAddNewState);
    }

    /**
     * 更新配置中心事件
     *
     * @param oldEvent 待更新事件
     * @param newEvent 更新后事件
     * @return
     * @throws NacosException
     */
    @Override
    public boolean updateEvents(String oldEvent, String newEvent) throws NacosException {
        if (StringUtils.isBlank(oldEvent))
            throw new StateMachineException(ResultEnum.OLD_EVENT_NULL);
        if (StringUtils.isNotBlank(newEvent))
            throw new StateMachineException(ResultEnum.NEW_EVENT_NULL);
        //获取配置中心配置
        String config = nacosOperationService.getConfig();
        //删除旧事件
        String configAfterDelOldEvent = delEventConfig(config, oldEvent);
        //添加新事件
        String configAfterAddNewEvent = addEventConfig(configAfterDelOldEvent, newEvent);
        //配置中心进行发布
        return publishConfig(configAfterAddNewEvent);
    }

    /**
     * 更新流转状态
     *
     * @param oldTransition origin 流转状态
     * @param newTransition 更新后的流转状态
     * @return
     */
    @Override
    public boolean updateTransition(List<ConfigEntity> oldTransition, List<ConfigEntity> newTransition) throws NacosException {
        if (null == oldTransition || oldTransition.size() == 0) {
            throw new StateMachineException(ResultEnum.NO_DEFINE_ORIGIN_TRANSITION);
        }
        if (null == newTransition || newTransition.size() == 0) {
            throw new StateMachineException(ResultEnum.NO_DEFINE_NEW_TRANSITION);
        }
        if (oldTransition.size() != newTransition.size()) {
            throw new StateMachineException(ResultEnum.PARAM_SIZE_INCONFORMITY);
        }
        //获取配置中心参数
        String config = nacosOperationService.getConfig();
        //批量删除旧Transition
        String configAfterDelOldTransition = delTransitionConfig(config, oldTransition);
        //批量新增新Transition
        String configAfterAddNewTransition = addTranstion(configAfterDelOldTransition, newTransition);
        //配置中心进行发布
        return publishConfig(configAfterAddNewTransition);
    }

    /**
     * 不同版本的nacos，拉取到的config信息有的包含"\r",有的不包含。这里判断做一下兼容设置
     *
     * @param config
     * @return
     */
    public String slashR(String config) {
        String[] split = config.split("\n");
        return split[0].contains("\r") ? "\r" : "";
    }

    public String slashR(List<String> config) {
        ;
        return config.get(0).contains("\r") ? "\r" : "";
    }
}
