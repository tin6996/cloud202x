package com.cjh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @projectName: cloud202x
 * @package: com.cjh.springcloud
 * @className: EurekaMain7002
 * @author: tinkouka
 * @date: 2022/11/25 19:15
 * @version: 1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaMain7002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7002.class,args);
    }
}
