package com.ju.service;

import java.util.List;

import com.ju.common.HigherResponse;
import com.ju.entity.Category;
import com.ju.entity.RCategory;

public interface CategoryService {
	
	HigherResponse<Category> getAllCategory();
	HigherResponse<Object> addOneCategory(Category c);
	HigherResponse<String> getCnameByCid(Integer cId);
	HigherResponse<List<RCategory>> getChildCategoryByCid(Integer cId);
	HigherResponse<List<Category>> getLeafCate();
	HigherResponse<List<Category>> getCategory(Integer pId);
	
	
	
	
	
}
