package com.rj.springcloud.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.rj.springcloud.entities.CommonResult;
import com.rj.springcloud.entities.Payment;
import com.rj.springcloud.ib.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @Author: rj
 * @Date: 2020-03-09 16:58
 * @Version: 1.0
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {
    private static final String PROVIDER_URL = "http://cloud-provider-payment";

    @Resource
    public RestTemplate restTemplate;

    @Resource
    public LoadBalancer loadBalancer;

    @Resource
    public DiscoveryClient discoveryClient;

    @GetMapping("/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PROVIDER_URL + "/payment/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PROVIDER_URL + "/payment/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/getByRibbon/{id}")
    public CommonResult<Payment> getByRibbon(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PROVIDER_URL + "/payment/payment/get/" + id, CommonResult.class);
        log.info(forEntity.getStatusCode() + "\t" + forEntity.getStatusCodeValue());
        if (forEntity.getStatusCode().is2xxSuccessful()) {
            return forEntity.getBody();
        } else {
            return new CommonResult<Payment>(444, "查询失败");
        }
    }

    @PostMapping("/createByRibbon")
    public CommonResult<Payment> post(Payment payment) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        JSONObject parse = JSONUtil.parseObj(payment);
        HttpEntity httpEntity = new HttpEntity(parse, requestHeaders);
        ResponseEntity<CommonResult> commonResultResponseEntity = restTemplate.postForEntity(PROVIDER_URL + "/payment/payment/create", httpEntity, CommonResult.class);
        log.info(commonResultResponseEntity.getStatusCode() + "\t" + commonResultResponseEntity.getStatusCodeValue());
        if (commonResultResponseEntity.getStatusCode().is2xxSuccessful()) {
            return commonResultResponseEntity.getBody();
        } else {
            return new CommonResult<Payment>(444, "查询失败");
        }
    }

    @GetMapping("/getLB")
    public String getPaymentLB() {
        List<ServiceInstance> list = discoveryClient.getInstances("cloud-provider-payment");
        if (list == null || list.size() < 0) {
            return null;
        }
        ServiceInstance instance = loadBalancer.instance(list);
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri+"/payment/payment/port",String.class);
    }
}
