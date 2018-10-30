package com.zhangsheng.syn;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import groovy.util.logging.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
	 * 用于测试redis秒杀（此java类是练习秒杀的业务场景）
	 */
	@RestController
	@RequestMapping("/api/spike")
	@Slf4j
	public class SpikeController {

	    @Resource(name = "stringRedisTemplate")
	    private StringRedisTemplate stringRedisTemplate;
	    @Autowired
		public RedisTemplate<String, Object> redisTemplate;
	    //记录实际卖出的商品数量(原子操作类AtomicInteger)
	    private AtomicInteger successNum = new AtomicInteger(0);

	    @RequestMapping(value = "/initSku", method = RequestMethod.GET)
	    public String initSku() {
	        //初始化库存数量
	        stringRedisTemplate.opsForValue().set("product_sku", "800");
	        //初始化实际卖出的商品数量0
	        successNum.set(0);
	        return "初始化库存成功";
	    }

	    /**
	     * 会出现超卖情况的减少库存方式
	     * @return
	     */
	    @RequestMapping(value = "/reduceSku", method = RequestMethod.GET)
	    public String reduceSku() {
	        //读redis数据库的数据
	    	Integer sku = Integer.parseInt(stringRedisTemplate.opsForValue().get("product_sku"));
	        sku = sku - 1;
	        if (sku < 0) {
	            return "库存不足";
	        }
	        //写入redis数据库
	        stringRedisTemplate.opsForValue().set("product_sku", sku.toString());
	        //记录实际卖出的商品数量
	        return "减少库存成功,共减少" + successNum.incrementAndGet();
	    }
	    
	    /**
	     * 加入事务的减少库存方式（使用redis原生的sdk
			如下改造reduceSku()方法，作为一个新接口http://127.0.0.1:8888/api/spike/reduceSku3）
	     * @return 
	     */
	    @RequestMapping(value = "/reduceSku3", method = RequestMethod.GET)
	    public String reduceSku3() {
	        Jedis jedis = new Jedis("127.0.0.1", 6379);
	        List<Object> result ;
	        //redis 的事物 保证原子操作
	        Transaction transaction = null;
	        try {
	            //监视product_sku,主要是解决sku > 0这个地方的并发问题
	        	jedis.watch("product_sku");
	            int sku = Integer.parseInt(jedis.get("product_sku"));
	            //有可能几个线程获得的值都是大于0，进入if判断后，value变为0，如果不监视的话就会超卖。
	            if (sku > 0) {
	                //multi命令 由 EXEC 命令原子性(atomic)地执行
	            	//Redis Multi 命令用于标记一个事务块的开始
	            	transaction = jedis.multi();
	                transaction.set("product_sku", String.valueOf(sku - 1));
//	                int exp = 1/0;
	                result = transaction.exec();
	                if (result == null || result.isEmpty()) {
	                    System.out.println("Transaction error...");// 可能是watch-key被外部修改，或者是数据操作被驳回
//	                    transaction.discard();  //watch-key被外部修改时，discard操作会被自动触发
	                    return "Transaction error...";
	                }
	            } else {
	                return "库存不足";
	            }
	            return "减少库存成功,共减少" + successNum.incrementAndGet();
	        } catch (Exception e) {
	            transaction.discard();
	            return "fail";
	        }
	    }

	   /* 
	    * spring的redisTemplate执行事务


		注意： 若要使用spring的redisTemplate执行事务，需要在开启事务后执行一个redis的查询操作（但不能使用查询到的值）。
		原因有两点： 
		spring对redis事务的exec()方法返回结果做了处理（把返回值的 OK结果删掉）。 
		导致在事务中只有set等更新操作时，事务执行失败与成功返回的结果一样
		事务过程中查询redis的值只会在事务执行成功后才放回。而在事务执行过程中只会返回null
		接口http://127.0.0.1:8888/api/spike/reduceSku3是使用spring的redisTemplate执行事务的例子。代码如下
	  */
	    @RequestMapping(value = "/reduceSku2", method = RequestMethod.GET)
	    public String reduceSku2() {
	        stringRedisTemplate.setEnableTransactionSupport(true);
	        List<Object> results = stringRedisTemplate.execute(new SessionCallback<List<Object>>() {
	            @Override
	            public List<Object> execute(RedisOperations operations) throws DataAccessException {
	                operations.watch("product_sku");
	                String product_sku = (String) operations.opsForValue().get("product_sku");
	                operations.multi();
	                operations.opsForValue().get("product_sku");//必要的空查询
	                Integer sku = Integer.parseInt(product_sku);
	                sku = sku - 1;
	                if (sku < 0) {
	                    return null;
	                }
	                operations.opsForValue().set("product_sku", sku.toString());
	                return operations.exec();
//	                    operations.unwatch(); //执行exec()后自动unwatch()

	            }
	        });

	        if (results != null && results.size() > 0) {
	            return "减少库存成功,共减少" + successNum.incrementAndGet();
	        }

	        return "库存不足";
//	            return result.toString();
	    }

      
	    
	    
	    

	    @RequestMapping(value = "/successNum", method = RequestMethod.GET)
	    public String successNum() {
	        return "顾客成功抢到的商品数量：" + successNum.get();
	    }
	    
	    
	    
	    
}

