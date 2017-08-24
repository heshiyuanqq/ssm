package com.hsy.ssm.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsy.ssm.dao.UserMapper;
import com.hsy.ssm.pojo.User;
import com.hsy.ssm.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserMapper userMapper;

	public User getUserById(long id) {
		System.out.println("UserServiceImpl类的具体方法中：UserServiceImpl="+this.getClass());
		System.out.println("UserServiceImpl中的具体方法 中：this.userMapper="+this.userMapper.getClass());
		return this.userMapper.selectByPrimaryKey(id);
	}

	public User getUserByIdWithOrders(long id) {
		return this.userMapper.getUserByIdWithOrderList(id);
	}

	public List<User> getUsersWithOrders() {
		return this.userMapper.getUsersWithOrderList();
	}
	
	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		for(int i=0;i<20000;i++){
			myRoundUp2PowerOfTwo(1000000000);
			//roundUpToPowerOf2(1000000000);
		}
		
		System.out.println("耗时："+( System.currentTimeMillis()-t1)+"毫秒！");
	}
	
	
	public static int myRoundUp2PowerOfTwo(int num){
		int capacity=1;
		while(capacity<num){
			capacity=capacity*2;
		}
		return capacity;
	}
	
	  public static int roundUpToPowerOf2(int num) {
	        return   Integer.highestOneBit((num - 1) << 1);
	  }

}
