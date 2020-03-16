package com.rj.springcloud.ib.impl;

import com.rj.springcloud.ib.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: rj
 * @Date: 2020-03-15 16:44
 * @Version: 1.0
 * 服务的机器下标 = 访问次数 % 机器总数
 */
@Component
public class LoadBancerImpl implements LoadBalancer {
    // 多线程环境使用 AtomicInteger 保持原子性、内存可见性、禁止指令重排
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;
        do{
            current = this.atomicInteger.get();
            // current >= Integer.MAX_VALUE  如果请求次数（current）大于Integer的最大范围，则重置为0
            next = current >= Integer.MAX_VALUE ? 0 : current+1;
        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("*********第几次访问，次数next:"+next);
        return next;
    }

    /**
     * @Author: rj
     * @Date: 2020-03-15
     * @Description: 从服务列表中获取当前使用的服务实例
     * @Param:
     * @Return:
     * @Version 1.0
     */
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
