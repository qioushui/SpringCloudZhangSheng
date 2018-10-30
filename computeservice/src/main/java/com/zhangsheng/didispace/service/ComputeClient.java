package com.zhangsheng.didispace.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="computeservice")//注解来绑定该接口对应compute-service服务
//接口 服务(提供各个服务上面公共的方法的接口：/add)
public interface ComputeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);
    
    @RequestMapping(method = RequestMethod.GET, value = "/api/spike/reduceSku2")
    void reduceSku2();
    
}