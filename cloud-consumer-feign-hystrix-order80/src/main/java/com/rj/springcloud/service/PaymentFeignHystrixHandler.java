package com.rj.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @Author: rj
 * @Date: 2020-03-16 11:34
 * @Version: 1.0
 */
@Component
public class PaymentFeignHystrixHandler implements PaymentFeignService {
    @Override
    public String paymentInfo_ok(Integer id) {
        return "paymentInfo_ok 执行出错了";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "paymentInfo_timeout 执行出错了";
    }
}
