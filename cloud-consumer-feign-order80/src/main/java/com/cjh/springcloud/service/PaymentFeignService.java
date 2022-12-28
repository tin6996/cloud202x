package com.cjh.springcloud.service;

import com.cjh.springcloud.entity.CommonResult;
import com.cjh.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @projectName: cloud202x
 * @package: com.cjh.springcloud.service
 * @interfaceName: PaymentService
 * @author: tinkouka
 * @date: 2022/12/11 23:32
 * @version: 1.0
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();


    //getForEntity返回的是ResponseEntity 包含头信息，状态码
    @GetMapping(value="/payment2/get/{id}")
    public CommonResult<Payment> getPaymentById2(@PathVariable("id") Long id);

    @PostMapping(value="/payment/create")
    public CommonResult create(@RequestBody Payment payment);

    @PostMapping(value="/payment/create2")
    public CommonResult create2(@RequestBody Payment payment);

}
