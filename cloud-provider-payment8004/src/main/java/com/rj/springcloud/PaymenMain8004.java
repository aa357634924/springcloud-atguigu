package com.rj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: rj
 * @Date: 2020-03-10 20:12
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymenMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymenMain8004.class,args);
    }
}
