package com.cjh.springcloud.dao;

import com.cjh.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @projectName: cloud202x
 * @package: com.cjh.springcloud.dao
 * @interfaceName: PaymentDao
 * @author: tinkouka
 * @date: 2022/11/12 12:36
 * @version: 1.0
 */

@Mapper //お勧め。データベースとのやり取り
public interface PaymentDao {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
