package com.xiaoer.manage.entity;

import java.util.List;

public class JsonType<T> {
	private int code =200;
	private String msg ="success";
	private List<T> data=null;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public JsonType(int code,String msg,List<T> data) {
		this.code=code;
		this.msg=msg;
		this.data=data;
	}
	
	public JsonType(List<T> data) {
		this.data=data;
	}
	public JsonType() {
	}
	
}
