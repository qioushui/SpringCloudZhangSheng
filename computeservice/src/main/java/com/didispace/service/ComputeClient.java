package com.didispace.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("computeservice")//注解来绑定该接口对应compute-service服务
//接口 服务
public interface ComputeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/addFeign")
    Integer addFeign(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);

}