package com.rj.springcloud.service;

import com.rj.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: rj
 * @Date: 2020-03-15 23:34
 * @Version: 1.0
 */
@Component
@FeignClient(value = "cloud-provider-hystrix-payment",fallback = PaymentFeignHystrixHandler.class)
public interface PaymentFeignService {
    @GetMapping(value="/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable("id") Integer id);
}
