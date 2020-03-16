package com.rj.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author: rj
 * @Date: 2020-03-10 22:28
 * @Version: 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    public static String URL = "http://cloud-provider-payment";

    @Value("server.port")
    private String port;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public String test(){
        return restTemplate.getForObject(URL+"/payment/payment/test",String.class);
    }
}
