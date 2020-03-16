package com.rj.springcloud.controller;

import com.rj.springcloud.entities.CommonResult;
import com.rj.springcloud.entities.Payment;
import com.rj.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: rj
 * @Date: 2020-03-09 15:29
 * @Version: 1.0
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;
    /**
     * 在跨系统访问的环境下，如果请求参数中不加@RequestBody，数据可以插入成功，但是真实数据存储的是null
     * */
    @PostMapping(value="/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("数据插入结果："+result);
        if(result > 0){
            return new CommonResult(200,"数据插入成功,server.port:"+port,result);
        }else{
            return new CommonResult(444,"数据插入失败",null);
        }
    }

    @GetMapping(value="/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment result = paymentService.getPaymentById(id);
        log.info("数据查询结果："+result+"。。。。。。");
        if(null != result){
            return new CommonResult(200,"数据查询成功,server.port:"+port,result);
        }else{
            return new CommonResult(444,"数据查询失败，查询ID："+id,null);
        }
    }

    @GetMapping("/port")
    public String getServer(){
        return port;
    }

    @GetMapping("/timeout")
    public String timeout(){
        try{
            TimeUnit.SECONDS.sleep(3);}catch (InterruptedException e) {e.printStackTrace();}
        return port;
    }
}
