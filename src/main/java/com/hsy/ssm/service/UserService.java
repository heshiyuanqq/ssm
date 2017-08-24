package com.hsy.ssm.service;

import java.util.List;

import com.hsy.ssm.pojo.User;

public interface UserService {

	public User getUserById(long id);
	public User getUserByIdWithOrders(long id);
	public List<User> getUsersWithOrders( );
}
