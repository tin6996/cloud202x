package com.cjh.springcloud.controller;

import com.cjh.springcloud.entity.CommonResult;
import com.cjh.springcloud.entity.Payment;
import com.cjh.springcloud.service.PaymentService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: cloud202x
 * @package: com.cjh.springcloud.controller
 * @className: PaymentController
 * @author: tinkouka
 * @date: 2022/11/15 21:56
 * @version: 1.0
 */
@RestController   //@Controller + @ResponseBody的组合
@Slf4j
public class PaymentController {

    @Resource
    //@Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    //服务发现
    @Resource
    //private DiscoveryClient discoveryClient;
    private EurekaDiscoveryClient eurekaDiscoveryClient;

    @PostMapping(value = "/payment/create")
    //@RequestBodyの使用：requestの際、bodyがJSONオブジェクトの場合、RequestBodyでJSON中のKEYとCLASS中のメンバーと一一対応する
    //restTemplate.postForObject(PAYMENT_URL + "/payment/create",payment,CommonResult.class)
    //paymentがオブジェクトですから、RequestBodyの使用が必要
    public CommonResult create(@RequestBody Payment payment){
        log.info("paymen8001>>>>" + payment.toString());
        int result = paymentService.create(payment);
        log.info("******入力結果:"+result);

        if(result > 0){
            return new CommonResult(200,"数据库插入成功,serverPort: " + serverPort,result);
        }else{
            return new CommonResult(444,"数据库插入失败,serverPort: " + serverPort,null);
        }
    }

    @PostMapping(value = "/payment/create2")
    //@RequestBodyを外す場合、http://localhost:8001/payment/create2?serial=123でOK
    public CommonResult create2(@RequestBody Payment payment){
        log.info("paymen8001 create2>>>>" + payment.toString());
        int result = paymentService.create(payment);
        log.info("******入力結果:"+result);

        if(result > 0){
            return new CommonResult(200,"数据库插入成功,serverPort: " + serverPort,result);
        }else{
            return new CommonResult(444,"数据库插入失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        log.info("*****/payment/get/:::" + id);
        Payment payment = paymentService.getPaymentById(id);
        log.info("******444查询结果:"+payment);
        if(payment != null){
            return new CommonResult(200,"查询成功,serverPort: " + serverPort,payment);
        }else{
            return new CommonResult(444,"查询失败,serverPort: " + serverPort,null);
        }
    }

    @GetMapping(value="/payment/discovery")
    public Object discovery(){
        List<String> services = eurekaDiscoveryClient.getServices();
        for (String str : services){
            log.info("******str: "+str);
        }

        List<ServiceInstance> serviceInstances = eurekaDiscoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance ser : serviceInstances){
            log.info("*****ins: "+ser.toString());
            log.info(ser.getServiceId()+"\t"+ser.getHost()+"\t"+ser.getPort()+"\t"+ser.getUri());

        }
        return this.eurekaDiscoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch(InterruptedException e){
            e.getStackTrace();
        }
        return serverPort;
    }

    /**
     * test~test5是针对RequestBody和RequestParam的使用测试
     * 详情可以参照已保存好的pdf文件
     * D:\01_work\Springboot学习
     */

    /**
     *  POST
     *  http://localhost:8001/payment/test?serial=123  NG
     *
     *  http://localhost:8001/payment/test
     *  Headers: Content-Type=:pplication/json;charset=UTF-8
     *  Body: {"serial":12345654321}                   OK
     */
    @PostMapping(value = "/payment/test")
    public String testPost(@RequestBody Payment payment){
        log.info("testPost1>>>>>");
        return "payment:::::" + payment.toString();
    }

    /**
     * POST
     * http://localhost:8001/payment/test2?serial=123　OK
     */
    @PostMapping(value = "/payment/test2")
    //@RequestBodyを外す場合、http://localhost:8001/payment/create2?serial=123でOK
    public String testPost2(Payment payment){
        log.info("testPost2>>>>>");
        return "payment:::::" + payment.toString();
    }

    /**
     * GET
     * http://localhost:8001/payment/test3?serial=123　OK
     */
    @GetMapping(value = "/payment/test3")
    public String testPost3(Payment payment){
        log.info("testPost3>>>>>");
        return "payment:::::" + payment.toString();
    }

    /**
     *  POST
     *  http://localhost:8001/payment/test4?serial=123で下記のエラーが発生する。
     *
     *  Resolved [org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException:
     *  Failed to convert value of type 'java.lang.String' to required type 'com.cjh.springcloud.entity.Payment';
     *  nested exception is java.lang.IllegalStateException:
     *  Cannot convert value of type 'java.lang.String' to required type 'com.cjh.springcloud.entity.Payment':
     *  no matching editors or conversion strategy found]
     */
    @PostMapping(value = "/payment/test4")
    public String testPost4(@RequestParam("serial") Payment payment){
        log.info("testPost4>>>>>");
        return "payment:::::" + payment.toString();
    }

    /**
     *  POST
     *  http://localhost:8001/payment/test4?serial=123で問題なし。
     */
    @PostMapping(value = "/payment/test5")
    public String testPost5(@RequestParam("serial") String serial){
        log.info("testPost4>>>>>");
        return "serial:::::" + serial;
    }


}
