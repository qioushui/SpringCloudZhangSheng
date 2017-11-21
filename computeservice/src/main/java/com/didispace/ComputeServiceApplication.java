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
/** 
 * @EnableAutoConfiguration:spring boot的注解，一般只用于主类， 
 * 是无xml配置启动的关键部分,明确指定了扫描包的路径为其修饰的主类的包（这也就是为什么主类要放在根包路径下的原因） 
 *  
 * @ComponentScan 进行包的扫描，扫描路径由@EnableAutoConfiguration指定了 
 *  
 * 主类要位于根包路径下，方便之后的扫描(We generally recommend that you locate your main application class in a root package above other classes.) 
 */  

@SpringBootApplication //相当于 @Configuration+@EnableAutoConfiguration+@ComponentScan 
//启动swagger注解启动该注解使得用在controller中的swagger注解生效，覆盖的范围由@ComponentScan的配置来指定，这里默认指定为根路径"com.didispace"下的所有controller  
@EnableDiscoveryClient
@EnableFeignClients //注解开启Feign功能   在应用主类中通过@EnableFeignClients注解开启Feign功能
public class ComputeServiceApplication {
	  /** 
	    * spring boot的入口，在整个子项目在内， 
	    * 只能有一个main方法，否则spring boot启动不起来 
	    */  
	public static void main(String[] args) {
		new SpringApplicationBuilder(ComputeServiceApplication.class).web(true).run(args);
		//SpringApplication.run(ComputeServiceApplication.class, args);
	}
	   /** 
     *  1.需要先定义一个convert转换消息的对象； 
        2.添加fastJson的配置信息，比如：是否要格式化返回的json数据； 
        3.在convert中添加配置信息 
        4.将convert添加到converters中 
     */  
    
	
	
	/*@Override  
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {  
        // TODO Auto-generated method stub  
        super.configureMessageConverters(converters);  
        //1.需要先定义一个convert转换消息的对象；  
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();  
        //2.添加fastJson的配置信息，比如：是否要格式化返回的json数据；  
        FastJsonConfig fastJsonConfig = new FastJsonConfig();  
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);  
        //3.在convert中添加配置信息  
        fastConverter.setFastJsonConfig(fastJsonConfig);  
        //4.将convert添加到converters中  
        converters.add(fastConverter);  
          
    }  
*/      

}
