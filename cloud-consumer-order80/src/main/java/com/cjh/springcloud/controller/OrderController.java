package com.cjh.springcloud.controller;

import com.cjh.springcloud.entity.CommonResult;
import com.cjh.springcloud.entity.Payment;
import com.cjh.springcloud.lb.LoadBalancer;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

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
public class OrderController {
    //public static String PAYMENT_URL = "http://localhost:8001";
    public static String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    //如果要使用自定义算法的负载均衡的话，就要注入该类，生成负载均衡实例
    //同时，自定义ApplicationContextConfig配置类里面的@LoadBalanced的注解也应该停止使用
    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private EurekaDiscoveryClient discoveryClient;

    //getForObject 返回的是类似于Json字符串，当下最流行
    @GetMapping(value="/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id,CommonResult.class);
    }

    //getForEntity返回的是ResponseEntity 包含头信息，状态码
    @GetMapping(value="/consumer/payment2/get/{id}")
    public CommonResult<Payment> getPaymentById2(@PathVariable("id") Long id){

        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id,CommonResult.class);

        log.info("entity.toString::::" + entity.toString());
        log.info("entity.getStatusCode().is2xxSuccessful():::::" + entity.getStatusCode().is2xxSuccessful());
        log.info("entity.getHeaders().toString():::::" + entity.getHeaders().toString());

        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult(444,"失敗");
        }

    }

    // get也可以提交数据进行更改操作
    @GetMapping(value="/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        log.info("order80>>>>" + payment.toString());
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create",payment,CommonResult.class);
    }

    // post
    @PostMapping (value="/consumer/payment2/create")
    public CommonResult<Payment> create2(Payment payment){
        log.info("order80>>PostMapping>>" + payment.toString());
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create",payment,CommonResult.class);
    }

    @GetMapping(value="/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        log.info(instances.toString());

        if (instances == null || instances.size() <= 0){
            return null;
        }

        ServiceInstance instance = loadBalancer.instances(instances);
        //URI uri =
        URI uri = instance.getUri();
        log.info("***** uri" + uri);

        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }


}
