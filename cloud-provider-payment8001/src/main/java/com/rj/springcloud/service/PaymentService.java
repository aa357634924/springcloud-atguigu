package com.rj.springcloud.service;

import com.rj.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: rj
 * @Date: 2020-03-09 15:25
 * @Version: 1.0
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
