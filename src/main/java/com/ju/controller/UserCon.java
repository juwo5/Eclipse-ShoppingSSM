package com.ju.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ju.common.HigherResponse;
import com.ju.entity.User;
import com.ju.service.UserService;

@RestController
@RequestMapping("/manage/user")
public class UserCon {

	@Autowired
	private UserService userService;

	//管理员登录
	@RequestMapping("/login.do")
	public HigherResponse<User> login(
			@RequestParam("name") String username,
			@RequestParam("pws") String password) {
		User user = new User(); 
		user.setUserName(username);
		user.setPassWord(password);
		HigherResponse<User> queryUser = userService.queryUser(user);	
		return queryUser;
	}
	
	
	//分页
	@RequestMapping("/list.do")
	public HigherResponse<Object> pageCon(
			//必须传pageNum值，或者默认为第一页和条数3
			@RequestParam(required=true,defaultValue="1")Integer pageNum,
			@RequestParam(required=true,defaultValue="3") Integer pageSize){
				return userService.pageQueryUser(pageNum, pageSize);
		//默认是第一页。
	}
	
	//普通用户
	//前台检查用户名是否存在
	@RequestMapping("/check_valid.do")
	public HigherResponse<User> checkNameOrEmailIsExists(String val, String type){
		return userService.checkNameOrEmailIsExists(val, type);	
	}
	//用户注册
	@RequestMapping("/register.do")
	public HigherResponse<Boolean> register(User user){
		return userService.register(user);
	}
	//普通用户登录添加到session
	@RequestMapping("/user/login.do")
	public HigherResponse<User> userLogin(String userName,String psw,HttpServletRequest request){
		HttpSession session = request.getSession();
		return userService.userLogin(userName, psw, session);
	}
	
	
	
	
	
	
	
	
	
}
