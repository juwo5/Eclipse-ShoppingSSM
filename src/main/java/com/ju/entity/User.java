package com.ju.entity;

import java.sql.Timestamp;

import lombok.Data;//注入实体类getset
@Data
public class User {

	private Integer id;

	private String userName;

	private String passWord;

	private String email;

	private String phone;

	private String question;

	private String answer;
	
	private Integer role;
	
	private Timestamp createTime;
	
	private Timestamp updateTime;

	public User(Integer id, String userName, String passWord, String email, String phone, String question,
			String answer, Integer role, Timestamp createTime, Timestamp updateTime) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
		this.phone = phone;
		this.question = question;
		this.answer = answer;
		this.role = role;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	

}
