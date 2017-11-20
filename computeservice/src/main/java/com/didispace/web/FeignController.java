package com.didispace.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didispace.service.ComputeClient;
@RestController
public class FeignController {
	 @Autowired
	    ComputeClient computeClient; 

	    @RequestMapping(value = "/addFeign", method = RequestMethod.GET)
	    public Integer addFeign() {
	        return computeClient.addFeign(10, 20);
	    }
}
