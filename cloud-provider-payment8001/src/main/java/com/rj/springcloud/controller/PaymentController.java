package com.rj.springcloud.controller;

import com.rj.springcloud.entities.CommonResult;
import com.rj.springcloud.entities.Payment;
import com.rj.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
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

    /* 获取配置文件中的port值 */
    @Value("${server.port}")
    private String port;

    /* 获取注册中心服务信息的对象 */
    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 在跨系统访问的环境下，如果请求参数中不加@RequestBody，数据可以插入成功，但是真实数据存储的是null
     * */
    @PostMapping(value="/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("数据插入结果："+result);
        if(result > 0){
            return new CommonResult(200,"数据插入成功,server.port："+port,result);
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

    @GetMapping("/eurekaInfo")
    public Object getEurekaInfo(){
        List<String> services = discoveryClient.getServices();
        services.forEach(str->log.info("*******"+str));

        services.forEach(str->{
            List<ServiceInstance> instances = discoveryClient.getInstances(str);
            instances.forEach(ins->log.info(str+"\t"+ins.getHost()+"\t"
            +ins.getPort()+"\t"+ins.getUri()+"\t"+ins.getMetadata()
            ));
        });
        return discoveryClient;
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
