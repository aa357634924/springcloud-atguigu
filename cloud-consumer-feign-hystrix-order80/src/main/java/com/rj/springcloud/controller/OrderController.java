package com.rj.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.rj.springcloud.entities.CommonResult;
import com.rj.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: rj
 * @Date: 2020-03-15 23:40
 * @Version: 1.0
 */
@RestController
@Slf4j
@RequestMapping("/order")
@DefaultProperties(defaultFallback = "global_fallback")
public class OrderController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        String result = paymentFeignService.paymentInfo_ok(id);
        return result;
    }
    @GetMapping("/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_fallback",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
    })
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        String result = paymentFeignService.paymentInfo_timeout(id);
        return result;
    }

    @GetMapping("/hystrix/timeout2/{id}")
    @HystrixCommand
    public String paymentInfo_Timeout2(@PathVariable("id") Integer id){
        String result = paymentFeignService.paymentInfo_timeout(id);
        return result;
    }

    public String paymentInfo_fallback(Integer id) {
        return "线程池： "+Thread.currentThread().getName()+" 80系统繁忙或自己运行出错，请稍后重试，id: "+id+" o(╥﹏╥)o";
    }

    public String global_fallback() {
        return "线程池： "+Thread.currentThread().getName()+" 通用异常返回信息 o(╥﹏╥)o";
    }
}
