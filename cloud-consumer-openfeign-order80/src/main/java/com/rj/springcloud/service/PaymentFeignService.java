package com.rj.springcloud.service;

import com.rj.springcloud.entities.CommonResult;
import com.rj.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: rj
 * @Date: 2020-03-15 20:38
 * @Version: 1.0
 */
@Component
@FeignClient(value = "cloud-provider-payment")
public interface PaymentFeignService {

    @GetMapping(value="/payment/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/payment/timeout")
    public String timeout();
}
