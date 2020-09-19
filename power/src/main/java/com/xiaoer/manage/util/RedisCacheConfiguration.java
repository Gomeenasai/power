package com.xiaoer.manage.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
@AutoConfigureAfter(RedisCacheConfiguration.class)
public class RedisCacheConfiguration {

//	@Bean
//	public RedisTemplate<String,Serializable> redisCacheTemplate(LettuceConnectionFactory redisConnectionFactory){
//		RedisTemplate<String,Serializable> template =new RedisTemplate<>();
//		template.setKeySerializer(new StringRedisSerializer());
////		template.setValueSerializer(new StringRedisSerializer());
//		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//		template.setConnectionFactory(redisConnectionFactory);
//		return template;
//	}
	 @Autowired
	    private RedisTemplate<Object,Object> redisTemplate;

	    @PostConstruct
	    public void init() {
	        initRedisTemplate();
	    }

	    private void initRedisTemplate() {
	        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
	        redisTemplate.setKeySerializer(stringSerializer);
	        redisTemplate.setHashKeySerializer(stringSerializer);
	        redisTemplate.setValueSerializer(stringSerializer);
	        redisTemplate.setHashValueSerializer(stringSerializer);
	    }
}
