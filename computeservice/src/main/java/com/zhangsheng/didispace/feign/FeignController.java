package com.zhangsheng.didispace.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhangsheng.didispace.service.ComputeClient;

@RestController
public class FeignController {
	 	@Autowired
	   ComputeClient computeClient; 

	  @RequestMapping(value = "/addFeign", method = RequestMethod.GET)
	   public Integer addFeign() {
	       return computeClient.add(10, 20);
	   }
}
