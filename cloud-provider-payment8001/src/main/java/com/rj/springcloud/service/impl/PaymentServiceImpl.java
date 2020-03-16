package com.rj.springcloud.service.impl;

import com.rj.springcloud.dao.PaymentDao;
import com.rj.springcloud.entities.Payment;
import com.rj.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: rj
 * @Date: 2020-03-09 15:26
 * @Version: 1.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
