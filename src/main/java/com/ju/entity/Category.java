package com.ju.entity;

import java.util.Date;

import lombok.Data;
@Data
public class Category {
	private Integer id;
	private Integer parent_id;
	private String name;
	private Integer status;
	private Date create_time;
	private Date update_time;
	private Byte is_leaf;
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(Integer id, Integer parent_id, String name, Integer status, Date create_time, Date update_time,
			Byte is_leaf) {
		super();
		this.id = id;
		this.parent_id = parent_id;
		this.name = name;
		this.status = status;
		this.create_time = create_time;
		this.update_time = update_time;
		this.is_leaf = is_leaf;
	}
	
	
	
	

}
