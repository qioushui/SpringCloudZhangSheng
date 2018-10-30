package com.zhangsheng.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

/**
 * @author zhangsheng
 * @version 1.0.0
 * @date 18/10/11 下午3:19
 * @blog 
 */
@Configuration
public class RedisConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

   /*@Bean
    public RedisTemplate<String, User> redisTemplateUser(RedisConnectionFactory factory) {
        RedisTemplate<String, User> template = new RedisTemplate<String, User>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }*/
   /* @Bean
    public RedisTemplate<String, UserInfo> redisTemplateUserInfo(RedisConnectionFactory factory) {
        RedisTemplate<String, UserInfo> redisTemplateUserInfo = new RedisTemplate<String, UserInfo>();
        redisTemplateUserInfo.setConnectionFactory(jedisConnectionFactory());
        redisTemplateUserInfo.setKeySerializer(new StringRedisSerializer());
        redisTemplateUserInfo.setValueSerializer(new RedisObjectSerializer());
        return redisTemplateUserInfo;
    }*/
    //此bean 是通用的操作对象的缓存配置
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();

		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializer());
		template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
		template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
		template.afterPropertiesSet();

		return template;

	}

}