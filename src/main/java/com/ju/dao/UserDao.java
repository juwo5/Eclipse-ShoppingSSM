package com.ju.dao;

import java.util.List;

import com.ju.entity.User;

public interface UserDao {
	//mybatis动态代理：mapper
	//查询所有用户(可以用于分页，得到总数)
	List<User> queryAllUser();
	//根据用户名和密码查询用户
	User queryUserByUserNameAndPsw(User user);
	//-------------普通用户----------
	
	//添加一个用户考虑用户是否存在
	Boolean addOneUser(User user);
		//考虑用户是否存在
	User checkNameOrEmailIsExists(User user);
	
	
	//登录接口添加到session
	public User queryNameAndPsw(User user);
	
}
