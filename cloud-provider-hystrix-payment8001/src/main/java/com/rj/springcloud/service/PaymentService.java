package com.rj.springcloud.service;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: rj
 * @Date: 2020-03-15 21:58
 * @Version: 1.0
 */
public interface PaymentService {
    public String paymentInfo_ok(Integer id);
    public String paymentInfo_Timeout(Integer id);
    public String paymentInfo_circuit(Integer id);
}
