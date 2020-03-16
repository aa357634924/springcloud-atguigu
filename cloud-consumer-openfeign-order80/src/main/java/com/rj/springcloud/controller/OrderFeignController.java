package com.rj.springcloud.controller;

import com.rj.springcloud.entities.CommonResult;
import com.rj.springcloud.entities.Payment;
import com.rj.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: rj
 * @Date: 2020-03-15 20:42
 * @Version: 1.0
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;
    @GetMapping(value="/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        CommonResult paymentById = paymentFeignService.getPaymentById(id);
        return paymentById;
    }

    @GetMapping("/timeout")
    public String timeout(){
        return paymentFeignService.timeout();
    }
}
