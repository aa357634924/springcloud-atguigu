package com.rj.springcloud.ib;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Author: rj
 * @Date: 2020-03-15 16:42
 * @Version: 1.0
 */
public interface LoadBalancer {
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
