package com.rj.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.rj.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @Author: rj
 * @Date: 2020-03-15 21:59
 * @Version: 1.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String paymentInfo_ok(Integer id) {
        return "线程池： "+Thread.currentThread().getName()+" paymentInfo_ok,id: "+id+"\t"+"O(∩_∩)O哈哈~";
    }

    /****************   服务降级    ****************/
    // 运行超时或出错，返回错误处理方法
    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_fallback",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")
    })
    public String paymentInfo_Timeout(Integer id) {
        int time = 2;
        try{
            TimeUnit.SECONDS.sleep(time);}catch (InterruptedException e) {e.printStackTrace();}
        return "线程池： "+Thread.currentThread().getName()+" paymentInfo_TimeOut,id: "+id+"\t"+"O(∩_∩)O哈哈~  耗时(秒):"+time;
    }


    public String paymentInfo_fallback(Integer id) {
        return "线程池： "+Thread.currentThread().getName()+" 8001系统繁忙或运行出错，请稍后重试，id: "+id+" o(╥﹏╥)o";
    }

    /*******************   服务熔断   ******************************/
    //  10秒内，10次访问的失败率达到60%，即启用备用处理方法（ 服务降级 -》进而熔断 -》恢复链路调用 ）
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallBack",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),// 时间窗口期
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60")// 失败率达到多少比例后跳闸
    })
    @Override
    public String paymentInfo_circuit(Integer id) {
        if(id < 0){
            throw new RuntimeException("***** id 不能为负数");
        }
        String uuid = IdUtil.simpleUUID();
        return "线程池： "+Thread.currentThread().getName()+" 调用成功，流水号："+uuid;
    }


    public String paymentCircuitBreaker_fallBack(Integer id) {
        return "id 不能为负数，请稍后再试！id："+id;
    }
}
