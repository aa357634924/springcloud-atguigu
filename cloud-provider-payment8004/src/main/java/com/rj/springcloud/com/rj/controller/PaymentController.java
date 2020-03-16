package com.rj.springcloud.com.rj.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Author: rj
 * @Date: 2020-03-10 20:30
 * @Version: 1.0
 */
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Value("server.port")
    private String port;

    @RequestMapping("/test")
    public Object test(){
        String str = port+"\t"+ UUID.randomUUID().toString();
        return str;
    }
}
