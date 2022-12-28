package com.cjh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @projectName: cloud202x
 * @package: com.cjh.springcloud.controller
 * @className: OrderMain80
 * @author: tinkouka
 * @date: 2022/12/2 17:03
 * @version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderConsulMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderConsulMain80.class,args);
    }
}
