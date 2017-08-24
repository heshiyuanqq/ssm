package com.hsy.ssm.pojo;

import java.io.Serializable;
import java.util.List;


public class User{
	private Long userId;
    private String userName;
    private Integer userMoney;
    private List<Orders>  orderList;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserMoney() {
		return userMoney;
	}
	public void setUserMoney(Integer userMoney) {
		this.userMoney = userMoney;
	}
	public List<Orders> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", userMoney=" + userMoney  + "]";
	}

    
}