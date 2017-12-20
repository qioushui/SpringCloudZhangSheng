package com.didispace.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ComputeController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;  //服务的 注册的 ip 服务名 地址 等信息  的查看（Eureka 的实例对象）
   /* @Autowired  
    private Userservice sinotransOrgRepository;  */
    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a + b;
       logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return r;
    }
    /**  
     * 请求内容是一个json串,spring会自动把他和我们的参数bean对应起来,不过要加@RequestBody注解  
     *   
     * @param sinotransOrg  
     * @return  
     */    
  /*  @RequestMapping(value = "/sinotransOrg3", method = { RequestMethod.POST, RequestMethod.GET })    
    public User sinotransOrgAdd(@RequestBody User user) {   
           
    	return sinotransOrgRepository.save(user);  
    } */ 
   
}