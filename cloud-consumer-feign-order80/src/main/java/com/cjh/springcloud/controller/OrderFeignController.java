package com.cjh.springcloud.controller;

import com.cjh.springcloud.entity.CommonResult;
import com.cjh.springcloud.entity.Payment;
import com.cjh.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        return paymentFeignService.paymentFeignTimeout();
    }


    //getForEntity返回的是ResponseEntity 包含头信息，状态码
    @GetMapping(value="/consumer/payment2/get/{id}")
    public CommonResult<Payment> getPaymentById2(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById2(id);
    }

    // get也可以提交数据进行更改操作
    //GET: http://localhost/consumer/payment/create?serial=fffff3
    @GetMapping(value="/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        log.info("order80>>>>" + payment.toString());
        return paymentFeignService.create(payment);
    }

    // post
    @PostMapping (value="/consumer/payment/create2")
    public CommonResult create2(Payment payment){
        log.info("order80>>PostMapping>>" + payment.toString());
        return paymentFeignService.create2(payment);
    }
}
