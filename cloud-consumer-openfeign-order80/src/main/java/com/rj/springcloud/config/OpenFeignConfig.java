package com.rj.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: rj
 * @Date: 2020-03-15 21:23
 * @Version: 1.0
 * 配置openfeign的打印日志级别
 */
@Configuration
public class OpenFeignConfig {
    @Bean
    Logger.Level feingLoggerLevel(){
        return Logger.Level.FULL;
    }
}
