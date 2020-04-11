package com.entity;

public class User {
	public String name;
	public String pwd;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String name,String pwd) {
		this.name=name;
		this.pwd=pwd;
		
	}
}
