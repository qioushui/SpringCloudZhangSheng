package com.didispace;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;



/*注解，该注解能激活Eureka中的DiscoveryClient实现，
才能实现Controller中对服务信息的输出。(允许服务被发现，被调用)
Fengn 实现的声明式服务调用客户端
*
*/

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients //注解开启Feign功能   在应用主类中通过@EnableFeignClients注解开启Feign功能
public class ComputeServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ComputeServiceApplication.class).web(true).run(args);
		//SpringApplication.run(ComputeServiceApplication.class, args);
	}

}
