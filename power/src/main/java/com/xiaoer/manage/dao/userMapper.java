package com.xiaoer.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.xiaoer.manage.entity.Users;

@Mapper
public interface userMapper {
	@Select("select * from users")
	@Results(id="userMap",value= {
		@Result(property="merchant",column="merchant",jdbcType=JdbcType.VARCHAR),
		@Result(property="class",column="class",jdbcType=JdbcType.VARCHAR),
		@Result(property="goods",column="godds",jdbcType=JdbcType.VARCHAR),
		@Result(property="menu_pic",column="menu_pic",jdbcType=JdbcType.VARCHAR),
		@Result(property="order",column="order",jdbcType=JdbcType.VARCHAR),
		@Result(property="user_address",column="user_address",jdbcType=JdbcType.VARCHAR),
		@Result(property="user_information",column="user_information",jdbcType=JdbcType.VARCHAR,id=true),
	})
	List<Users> selectUsers();
	
	@Select("select * from users where account=#{account}")
	@ResultMap(value="userMap")
	Users findUser(String account);
	
	@Insert("insert into users(account,area,percent,phone,state,authority,name,nickname,wechat_img,pid,pass)values(#{account},#{area},#{percent},#{phone},#{state},#{authority},#{name},#{nickname},#{wechat_img},#{pid},#{pass})")
	boolean addUser(Users user);
	
	@Update("update users set account=#{account},area=#{area},percent=#{percent},phone=#{phone},state=#{state},authority=#{authority},name=#{name},nickname=#{nickname},wechat_img=#{wechat_img},pid=#{pid},pass=#{pass} where account=#{account}")
	boolean updateUser(Users user);
	
	@Delete("delete from users where account=#{account}")
	boolean delUser(String account);

}
