package com.xiaoer.manage;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.xiaoer.manage.dao.userMapper;
import com.xiaoer.manage.entity.Users;
import com.xiaoer.manage.util.RedisUtil;

@SpringBootTest
class PowerApplicationTests {

	@Autowired
	private userMapper usermapper;
	
	@Autowired
	private StringRedisTemplate template;
	@Resource
	private RedisUtil redis;
	@Test
	public void contextLoads() {
	Users users=usermapper.findUser("test1");
	template.opsForValue().set("user", "users");
	redis.set("user2", users.toString());
	System.out.println("======="+template.opsForValue().get("user")+"=======");
	System.out.println("======="+redis.get("user2")+"=======");
	}

}
