package com.zhangsheng.syn;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/MiaoSha")
public class MiaoShaController {
	@Autowired
    private StringRedisTemplate redisTemplate;
//秒杀开始
	/* @RequestMapping("/wyh_begin_kill")
    public String message(@RequestParam(value = "phone") String phone, @RequestBody SeckillEntity seckillEntity) {
        String key = phone + "_" + seckillEntity.getSeckill_id();//设置redis的key值
        String state = redisTemplate.opsForValue().get(key);//根据用户手机号从redis缓存中查询是否预约
        if (null == state) {//第一次进redis查询，为空，然后去数据库查询是否预约秒杀
            List<SuccessKillEntity> list = successKillMapper.wyh_select_state(phone);//根据用户手机号从数据库查询是否有该用户的预约信息
            if (null == list) {//没查到数据说明没有预约
                System.out.println("该用户没有预约");
            } else {
                synchronized (this) {//否则更改redis状态，使下一次免查询
                    redisTemplate.opsForValue().set(phone + "_" + seckillEntity.getSeckill_id(), String.valueOf(list.get(0).getState()), 300, TimeUnit.SECONDS);
                    state = String.valueOf(list.get(0).getState());//修改状态
                }
            }
        }

        if (state.equals("-1")) {
            List values = redisTemplate.opsForHash().values(seckillEntity.getSeckill_id() + "");//从redis查询秒杀商品信息：秒杀开始和结束时间以及商品数量，没有查到就从数据库查询
            if (values.size() == 0) {
                List<SeckillEntity> list = seckillMapper.wyh_select_seckill(String.valueOf(seckillEntity.getSeckill_id()));//从数据库查询秒杀商品
                if (null == list) {//没查到数据说明没有此秒杀商品
                    System.out.println("没有该秒杀商品信息");
                }
                synchronized (this) {//上锁 - 把秒杀商品信息加入redis，下一次免查询
                    if (!redisTemplate.opsForHash().hasKey(seckillEntity.getSeckill_id() + "", "number")) {
                        HashMap<String, String> productHash = new HashMap<String, String>();
                        productHash.put("number", list.get(0).getNumber() + "");//放入商品数量进redis
                        productHash.put("start_time", list.get(0).getStart_time().getTime() + "");//换算开始时间为毫秒数
                        productHash.put("end_time", list.get(0).getEnd_time().getTime() + "");//换算结束时间为毫秒数
                        redisTemplate.opsForHash().putAll(list.get(0).getSeckill_id() + "", productHash);
                        redisTemplate.expire(list.get(0).getSeckill_id() + "", 300, TimeUnit.SECONDS);
                        values = redisTemplate.opsForHash().values(seckillEntity.getSeckill_id() + "");
                    }
                }
            }
            if (new Date(Long.valueOf((String) values.get(1))).after(new Date(System.currentTimeMillis()))) {//判断秒杀开始时间 - 毫秒数
                System.out.println("抢购还没有开始");
            } else if (new Date(Long.valueOf((String) values.get(2))).before(new Date(System.currentTimeMillis()))) {//判断秒杀结束时间 - 毫秒数
                System.out.println("抢购已经结束");
            } else {
                Long number = redisTemplate.opsForHash().increment(seckillEntity.getSeckill_id() + "", "number", -1);//从redis减少本次抢购商品 - 为负值说明数量为空 - 驳回请求
                if (number >= 0) {//还有库存
                    redisTemplate.opsForValue().increment(key, 1);//更改redis中此用户的抢购状态 - 本次抢购成功，以后不让抢购 - 限量
                    // 服务器闲暇时间将数据提交到数据库 - 尽量避免直接操作数据库 - 使用redis速度快 
                } else {
                    System.out.println("商品已被抢完");
                }
            }
        } else {
            System.out.println("您已抢购过该产品");
        }
        return null;
    }*/
}
