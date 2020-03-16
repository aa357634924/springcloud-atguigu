package com.rj.springcloud.controller;

import com.rj.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: rj
 * @Date: 2020-03-15 22:12
 * @Version: 1.0
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String server;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_ok(id);
        return result;
    }
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_Timeout(id);
        return result;
    }

    @GetMapping("/payment/hystrix/circuit/{id}")
    public String paymentInfo_circuit(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_circuit(id);
        return result;
    }
}
