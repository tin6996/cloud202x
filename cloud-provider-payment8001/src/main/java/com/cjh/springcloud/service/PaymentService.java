package com.cjh.springcloud.service;

import com.cjh.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @projectName: cloud202x
 * @package: com.cjh.springcloud.service
 * @interfaceName: PaymentService
 * @author: tinkouka
 * @date: 2022/11/15 21:25
 * @version: 1.0
 */
public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);

}
