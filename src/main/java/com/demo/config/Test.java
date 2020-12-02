package com.demo.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2020/12/2 17:21
 * @Created by fanshuaibing
 */
public class Test {
    public static void main(String[] args) throws NacosException {

        Properties properties = new Properties();
        String serverAddr = "10.7.6.14:8848";
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        String nameSpace = "f58c7c93-ed94-4b19-813a-221ac5671049";
        properties.put(PropertyKeyConst.NAMESPACE, nameSpace);
        ConfigService  configService = NacosFactory.createConfigService(properties);
        String content = configService.getConfig("spring-state-machine", "nsite.statemachine", 5000);
        System.out.println(content);
    }
}
