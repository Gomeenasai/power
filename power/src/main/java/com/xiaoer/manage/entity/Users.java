package com.xiaoer.manage.entity;
@Entity
public class Users {

	private Interger userid;
	private String username;
	private String password;

	public Interger getuserid() {
		return userid;
	}

	public void setuserid(Interger userid) {
		this.userid = userid;
	}

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
