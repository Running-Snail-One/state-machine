package com.demo.utils;

import com.demo.model.ConfigEntity;
import com.demo.model.rq.TransitionRQ;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname BeanUtils
 * @Description TODO
 * @Date 2020/12/1 15:41
 * @Created by fanshuaibing
 */
public class BeanUtil {
    public static List<ConfigEntity> TransitionRQ2ConfigEntity(List<TransitionRQ> rqList){
        if(rqList == null || rqList.size() == 0){
            return null;
        }
        List<ConfigEntity> configEntityLis = new ArrayList<>();
        ConfigEntity configEntity = null;
        try {
            for(TransitionRQ transitionRQ:rqList){
                configEntity = new ConfigEntity();
                BeanUtils.copyProperties(transitionRQ,configEntity);
                configEntityLis.add(configEntity);
            }
        } catch (BeansException e) {
            e.printStackTrace();
        }
        return configEntityLis;
    }
}
