package com.hsy.ssm.pojo;

import java.io.Serializable;

public class LoginUser implements Serializable{
	
	private static final long serialVersionUID = 2660910339320653401L;
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
