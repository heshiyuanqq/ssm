package com.hsy.ssm.pojo;

public class Orders {
	
	private long orderId;
	private String orderNo;
	private String orderName;
	private long orderUserId;
	private User user;
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public long getOrderUserId() {
		return orderUserId;
	}
	public void setOrderUserId(long orderUserId) {
		this.orderUserId = orderUserId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", orderNo=" + orderNo
				+ ", orderName=" + orderName + ", orderUserId=" + orderUserId + "]";
	}
	
	
	
}
