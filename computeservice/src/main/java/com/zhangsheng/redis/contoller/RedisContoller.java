package com.zhangsheng.redis.contoller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhangsheng.didispace.entity.User;
import com.zhangsheng.redis.util.RedisCacheUtil;
//建议使用json来存储
//正常情况下效率也挺高，但是如果再高并发的情况下，序列化和反序列化消耗太多，redis不支持存储object和泛型，是有理由的。
//把object和list<?> 转成json的字符串格式再set到redis里面，取得时候再把json转换为需要的对象，这样简单快捷，推荐使用
@Controller
public class RedisContoller {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisCacheUtil<User> redisCacheUtil;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	  
	@RequestMapping("/redis")
	@ResponseBody
	public String test() throws Exception {
		List<User> list = new ArrayList<User>();
		// 保存字符串
		stringRedisTemplate.opsForValue().set("aaa", "5565");
		//Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));

		// 保存对象
		User user = new User(123L,"超人", 20);
		redisCacheUtil.setCacheObject(user.getId().toString(), user);
		//redisTemplate.opsForValue().set(user.getId().toString(), user);
		list.add(user);
		User user2 = new User(253261L,"蝙蝠侠", 30);
		//redisTemplate.opsForValue().set(user.getId().toString(), user);
		redisCacheUtil.setCacheObject(user.getId().toString(), user);
		list.add(user2);
		User user3= new User(2541L,"蜘蛛侠", 40);
		list.add(user3);
		//redisTemplate.opsForValue().set(user.getId().toString(), user);
		redisCacheUtil.setCacheObject(user.getId().toString(), user);	
		redisCacheUtil.setCacheList("listData", list);
		return "sucess";

	}
	@RequestMapping("/getRedis")
	@ResponseBody
	public User getRedis(@RequestParam String a) throws Exception {
		System.out.println("w j lai ========"+redisTemplate.opsForValue().get(a));
		
				System.out.println("redisCacheUtil.getCacheObject()"+redisCacheUtil.getCacheObject(a));
		
				return redisCacheUtil.getCacheObject(a);

	}
	@RequestMapping("/getRedisList")
	@ResponseBody
	public List<User> getRedisList(@RequestParam String a) throws Exception {
		//System.out.println("w j lai ========"+redisTemplate.opsForValue().get(a));
	
		List<User> li = redisCacheUtil.getCacheList(a);
		//redisCacheUtil.getCacheObject(a);
		//System.out.println("redisCacheUtil.getCacheList(a)"+redisCacheUtil.getCacheList("listData"));
		//System.out.println("redisCacheUtil.getCacheList(a)"+redisCacheUtil.getCacheObject(a));
		//System.out.println("=================="+li.size());
		return  li;

	}

	
	// @RequestParam("username") String username, @RequestParam(value="age",required=false,defaultValue="0") int age
	
}
