package com.rj.springcloud.dao;

import com.rj.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: rj
 * @Date: 2020-03-09 14:42
 * @Version: 1.0
 */
@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
