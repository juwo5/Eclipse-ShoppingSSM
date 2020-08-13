package com.ju.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;
@Data//继承主要目录
public class RCategory extends Category{
	private List<RCategory> cates;

	public RCategory() {
	}

	public RCategory(Integer id, Integer parent_id, String name, Integer status, Date create_time, Date update_time,
			Byte is_leaf, List<RCategory> cates) {
		super(id, parent_id, name, status, create_time, update_time, is_leaf);
		this.cates = cates;
	}
	

	
	
	

	
	
	

}
