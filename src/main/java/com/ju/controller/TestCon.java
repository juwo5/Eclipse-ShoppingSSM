package com.ju.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ju.entity.User;
import com.ju.service.UserService;

@RestController
public class TestCon {
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/test.do")
	public List<User> test01(){
		List<User> queryAllUsers = userService.queryAllUsers();
		System.out.println(queryAllUsers);
		return queryAllUsers;
	}
}
