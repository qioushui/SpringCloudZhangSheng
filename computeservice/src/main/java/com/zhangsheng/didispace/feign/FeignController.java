package com.zhangsheng.didispace.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhangsheng.didispace.service.ComputeClient;

@RestController
public class FeignController {
	//注册负载均衡提供的客户端接口的接口（需要自己编写）
	  @Autowired
	  ComputeClient computeClient; 
	  //此接口提供给 业务方调用
	  @RequestMapping(value = "/addFeign", method = RequestMethod.GET)
	   public Integer addFeign() {
	     
		  return computeClient.add(10, 20);
	       
	   }
	  @RequestMapping(value = "/gaoBin", method = RequestMethod.GET)
	   public String gaoBinFeign() {
		  computeClient.reduceSku2();
		  return "sucess";
	       
	   }
}
