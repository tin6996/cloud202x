package com.cjh.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: cloud202x
 * @package: com.cjh.myrule
 * @className: MySelfRule
 * @author: tinkouka
 * @date: 2022/12/11 17:48
 * @version: 1.0
 */

/*
官方文档明确给出了警告：
        这个自定义配置类不能放在@ComponentScan所扫描的当前包下以及子包下，
        否则我们自定义的这个配置类就会被所有的Ribbon客户端所共享，达不到特殊化定制的目的了。
*/

@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        return new RandomRule(); //定义为随机
    }
}
