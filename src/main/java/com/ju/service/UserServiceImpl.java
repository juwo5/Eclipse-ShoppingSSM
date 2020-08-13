package com.ju.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ju.common.HigherResponse;
import com.ju.dao.UserDao;
import com.ju.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	public List<User> queryAllUsers() {
		return userDao.queryAllUser();
	}

	public HigherResponse<User> queryUser(User user) {
		if (null == user) {
			return HigherResponse.getResponseFailed("用户名或密码为空！！！");
		} else {
			if (null == user.getUserName() || "".equals(user.getUserName())) {
				return HigherResponse.getResponseFailed("用户名不能为空！！！");
			}
			if (null == user.getPassWord() || "".equals(user.getPassWord())) {
				return HigherResponse.getResponseFailed("密码不能为空！！！");
			}	
		}
		User queryUserByUserNameAndPsw = userDao.queryUserByUserNameAndPsw(user);
		if(null == queryUserByUserNameAndPsw) {
			return HigherResponse.getResponseFailed("用户名或密码错误！！！");
		}
		return HigherResponse.getResponseSuccess(queryUserByUserNameAndPsw);
	}

	public HigherResponse<Object> pageQueryUser(Integer pageNum, Integer pageSize) {
		//开启分页
				PageHelper.startPage(pageNum, pageSize);
				//数据总数
				List<User> queryAllUser = userDao.queryAllUser();
				//分页好的数据
				PageInfo<User> pageInfo = new PageInfo<User>(queryAllUser);
				if(null==pageInfo){
					return HigherResponse.getResponseFailed("查询失败");
				}else {
					return HigherResponse.getResponseSuccess(pageInfo);
				}
	}

	

	@Override
	public HigherResponse<User> checkNameOrEmailIsExists(String val, String type) {
		System.out.println("传来的值:"+val+type);
		if(StringUtils.isEmpty(val))
			return HigherResponse.getResponseFailed("用户名或邮箱为空");
		if(StringUtils.isEmpty(type))
			return HigherResponse.getResponseFailed("服务器异常");
		User user = new User();
		if("username".equals(type)){
			user.setUserName(val);
		}else if ("email".equals(type)) {
			user.setEmail(val);
		}
		User checkNameOrEmailIsExists = userDao.checkNameOrEmailIsExists(user);
		System.out.println(checkNameOrEmailIsExists);
		if(null!=checkNameOrEmailIsExists){
			return HigherResponse.getResponseFailed(val+"已存在");
		}
		return HigherResponse.getResponseSuccess("可以注册");
	}
	
	
	@Override
	public HigherResponse<Boolean> register(User user) {
		//判断是否为空，是否存在，不存在添加用户
		if(StringUtils.isEmpty(user))
			return HigherResponse.getResponseFailed("服务器异常");
		if(StringUtils.isEmpty(user.getUserName()))
			return HigherResponse.getResponseFailed("用户名不能为空");
		if(StringUtils.isEmpty(user.getEmail()))
			return HigherResponse.getResponseFailed("邮箱不能为空");
		if(StringUtils.isEmpty(user.getPassWord()))
			return HigherResponse.getResponseFailed("密码不能为空");
		Boolean addOneUser = userDao.addOneUser(user);
		if(false==addOneUser)
			return HigherResponse.getResponseFailed("注册失败，重新注册");
		return HigherResponse.getResponseSuccess("注册成功");
	}

	@Override
	public HigherResponse<User> userLogin(String userName, String psw,HttpSession session) {
		if(StringUtils.isEmpty(userName))
			return HigherResponse.getResponseFailed("用户名不能为空");
		if(StringUtils.isEmpty(psw))
			return HigherResponse.getResponseFailed("密码不能为空");
		User user=new User();
		user.setUserName(userName);
		user.setPassWord(psw);
		User queryNameAndPsw = userDao.queryNameAndPsw(user);
		if(StringUtils.isEmpty(queryNameAndPsw))
			return HigherResponse.getResponseFailed("用户名或密码错误");
		//放入session
		session.setAttribute("user", queryNameAndPsw);
		return HigherResponse.getResponseSuccess(queryNameAndPsw);
	}
	
	
}
