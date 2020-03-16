package com.rj.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: rj
 * @Date: 2020-03-09 17:01
 * @Version: 1.0
 * RestTemplate 是对httpclient的封装，模拟客户端请求
 * @LoadBalanced 赋予RestTemplate负载均衡的能力
 */
@Configuration
public class ApplicationConfig {
    @Bean
//    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
