package com.xiaoer.manage.server;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xiaoer.manage.entity.Users;

@Service
public interface userServer {
	List<Users> getUsers();
	Users findUser(String account);
	Boolean addUser(Users user);
	Boolean delUser(String account);
	Boolean updateUser(Users user);
}
