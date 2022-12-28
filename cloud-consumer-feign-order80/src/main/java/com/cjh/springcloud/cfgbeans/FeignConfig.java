package com.cjh.springcloud.cfgbeans;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: cloud202x
 * @package: com.cjh.springcloud.cfgbeans
 * @className: FeignConfig
 * @author: tinkouka
 * @date: 2022/12/12 0:32
 * @version: 1.0
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
