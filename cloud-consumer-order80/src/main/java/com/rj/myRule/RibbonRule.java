package com.rj.myRule;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: rj
 * @Date: 2020-03-13 15:06
 * @Version: 1.0
 * 自定义的负载策略，不能放到配置类@ComponentScan所扫描的当前包以及子类包下（如：springboot启动类的同级或子类下边）
 * 否则 自定义的这个配置类，就会被所有的Ribbon客户端所共享，达不到特殊化定制的目的
 */
@Configuration
public class RibbonRule {
    @Bean
    public IRule getRule(){
        return new RandomRule();// 定义一个随机访问的负载均衡策略
    }
}
