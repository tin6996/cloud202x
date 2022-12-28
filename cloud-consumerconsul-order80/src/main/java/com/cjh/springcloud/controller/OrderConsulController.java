package com.cjh.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @projectName: cloud202x
 * @package: com.cjh.springcloud.controller
 * @className: OrderController
 * @author: tinkouka
 * @date: 2022/11/21 17:13
 * @version: 1.0
 */
@RestController
@Slf4j
public class OrderConsulController {
    //public static String PAYMENT_URL = "http://localhost:8001";
    public static String PAYMENT_URL = "http://consul-provider-payment";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value="/consumer/payment/consul")
    public String paymentInfo(){
        log.info("orderZK80>>>>");
        return restTemplate.getForObject(PAYMENT_URL + "/payment/consul",String.class);
    }


}
