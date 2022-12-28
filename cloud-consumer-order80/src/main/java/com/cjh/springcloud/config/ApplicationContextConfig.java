package com.cjh.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @projectName: cloud202x
 * @package: com.cjh.springcloud.config
 * @className: ApplicationContextConfig
 * @author: tinkouka
 * @date: 2022/11/21 17:20
 * @version: 1.0
 */
@Configuration
public class ApplicationContextConfig {

    //如果要使用自定义算法的负载均衡的话，@LoadBalanced的注解应该停止使用
    @Bean             //把RestTemplate注入容器
    @LoadBalanced   //负载均衡的使用
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
//applicationContext.xml <bean id="" class="">>