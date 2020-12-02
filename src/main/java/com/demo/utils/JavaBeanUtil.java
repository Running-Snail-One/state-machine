package com.demo.utils;

import com.demo.model.ConfigEntity;
import com.demo.model.rq.TransitionRQ;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Classname BeanUtils
 * @Description TODO
 * @Date 2020/12/1 15:41
 * @Created by fanshuaibing
 */
public class JavaBeanUtil {

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

    /**
     * 判断list1 和 list2 是否存在交集
     * @param list1
     * @param list2
     * @return
     */
   public static boolean retain(List<Map<String,String>> list1,List<ConfigEntity> list2){
        for(Map map:list1){
            for(ConfigEntity configEntity:list2){
                if(map.get("source").equals(configEntity.getSource())
                &&map.get("target").equals(configEntity.getTarget())
                &&map.get("event").equals(configEntity.getEvent())){
                    return true;
                }
            }
        }
        return false;
   }
}
