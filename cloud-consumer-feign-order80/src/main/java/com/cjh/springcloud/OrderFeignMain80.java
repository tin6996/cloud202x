package com.cjh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @projectName: cloud202x
 * @package: com.cjh.springcloud
 * @className: OrderFeignMain80
 * @author: tinkouka
 * @date: 2022/12/11 23:27
 * @version: 1.0
 */
@SpringBootApplication
@EnableFeignClients
public class OrderFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class, args);
    }
}
