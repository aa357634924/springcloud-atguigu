package com.rj.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: rj
 * @Date: 2020-03-15 23:28
 * @Version: 1.0
 */
@Configuration
public class OpenFeignConfig {
    @Bean
    Logger.Level feingLoggerLevel(){
        return Logger.Level.FULL;
    }
}