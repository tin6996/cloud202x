package com.cjh.springcloud.service.impl;

import com.cjh.springcloud.dao.PaymentDao;
import com.cjh.springcloud.entity.Payment;
import com.cjh.springcloud.service.PaymentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @projectName: cloud202x
 * @package: com.cjh.springcloud.service.impl
 * @className: PaymentServiceImpl
 * @author: tinkouka
 * @date: 2022/11/15 21:33
 * @version: 1.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    //@Autowired //も可能
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(@Param("id") Long id){
        return paymentDao.getPaymentById(id);
    }

}
