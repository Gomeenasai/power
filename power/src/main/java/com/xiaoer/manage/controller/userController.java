package com.xiaoer.manage.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoer.manage.entity.Users;
import com.xiaoer.manage.server.userServer;

@RestController
@CrossOrigin
@RequestMapping("/users")
@MapperScan("com.xiaoer.manage.mappers")
public class userController {
	@Autowired
	private userServer userserver;
	
	@RequestMapping("/getusers")
	public List<Users> getUsers(){
		return userserver.getUsers();
	}
	
	@RequestMapping(value="/finduser",method=RequestMethod.POST)
	public Users finduser(@Param("account") String account) {
		Users user=userserver.findUser(account);
		user.setPass(null);
		return user;
	}
	@RequiresPermissions("1")
	@RequestMapping("/adduser")
	public String addUser(Users user) {
		System.out.println(user.toString());
		if(userserver.addUser(user)) {
			return "success";
		};
		return "error:添加失败";
	}
	@RequiresPermissions("2")
	@RequestMapping("/deluser")
	public String delUser(@Param("account")String account) {
		if(userserver.delUser(account)) {
			return "success";
		}
		return "fail";
	}
}
