package com.xiaoer.manage.util;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public final class RedisUtil {
	@Autowired
	private RedisTemplate<Object,Object> redisTemplate;
	
	
	//指定缓存失效时间
	public boolean expire(String key,long time) {
		try {
			if(time>0) {
				redisTemplate.expire(key,time,TimeUnit.SECONDS);
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//根据key获取过期时间 返回0永久有效
	public long getExpire(String key) {
		return redisTemplate.getExpire(key,TimeUnit.SECONDS);
	}
	
	//key是否存在
	public boolean hasKey(String key) {
		try {
			return redisTemplate.hasKey(key);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//删除缓存
	@SuppressWarnings("unchecked")
	public void del(String...key) {
		if(key!=null&&key.length>0) {
			if(key.length==1) {
				redisTemplate.delete(key[0]);
			}else {
				redisTemplate.delete(CollectionUtils.arrayToList(key));
			}
		}
	}
	
	//缓存获取
	public Object get(String key) {
		if(key!=null) {
			try {
				return redisTemplate.opsForValue().get(key);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	//缓存放入
	public Object set(String key,Object value) {
		try {
			redisTemplate.opsForValue().set(key,value);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//缓存放入
		public Object set(String key,Object value,long time) {
			try {
				if(time>0) {
				redisTemplate.opsForValue().set(key, value,time,TimeUnit.SECONDS);
				}else{
					set(key,value);
				};
				return true;
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		//递增
		public long incr(String key,long delta) {
			if(delta<0) {
				throw new RuntimeException("递减因子必须大于0");
			}
			return redisTemplate.opsForValue().increment(key, delta);
		}
		
		//递减
		public long decr(String key,long delta) {
			if(delta<0) {
				throw new RuntimeException("递减因子必须大于0");
			}
			return redisTemplate.opsForValue().increment(key, -delta);
		}
}
