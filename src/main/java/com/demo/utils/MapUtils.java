package com.demo.utils;

import com.demo.model.ConfigEntity;
import org.springframework.cglib.beans.BeanMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapUtils {
    /**
     * map -> List
     * @param lists
     * @return
     */
    public static List map2List(List<Map<String, String>> lists){
        ArrayList<Object> arr = new ArrayList<>();
        ConfigEntity configEntity = null;
        for (int i = 0; i < lists.size(); i++) {
            configEntity = new ConfigEntity();
            BeanMap beanMap = BeanMap.create(configEntity);
            beanMap.putAll(lists.get(i));
            arr.add(configEntity);
        }
        return arr;
    }
}
