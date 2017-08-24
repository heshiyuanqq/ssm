package com.hsy.ssm.dao;

import java.util.List;

import com.hsy.ssm.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);
    User getUserByIdWithOrderList(Long id);
    List<User> getUsersWithOrderList( );
    
    
    

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}