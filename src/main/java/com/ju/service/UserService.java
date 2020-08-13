package com.ju.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ju.common.HigherResponse;
import com.ju.entity.User;

public interface UserService {
	//-------------管理员----------
	//mybatis动态代理：mapper
	//查询所有用户
	List<User> queryAllUsers();
	//根据用户名密码查询用户信息
	HigherResponse<User> queryUser(User user);
	//分页查询用户列表
	HigherResponse<Object> pageQueryUser(Integer pageNum,Integer pageSize);
	
	//-------------普通用户----------
	//注册用户
	public HigherResponse<Boolean> register(User user);

	//检查用户名是否有效
	public HigherResponse<User> checkNameOrEmailIsExists(String val,String type);
	
	//登录接口role=0放到session和queryNameAndPsw（）
	HigherResponse<User> userLogin(String userName,String psw,HttpSession session);	
	
}
