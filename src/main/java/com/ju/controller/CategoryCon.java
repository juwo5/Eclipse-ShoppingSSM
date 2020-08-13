package com.ju.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ju.common.HigherResponse;
import com.ju.entity.Category;
import com.ju.entity.RCategory;
import com.ju.service.CategoryServiceImpl;

@RestController
@RequestMapping("/manage/category")
public class CategoryCon {
	@Autowired
	private CategoryServiceImpl cs;
	
	@RequestMapping("/get_allcategory.do")
	public HigherResponse<Category> getAllCategory(){

		return cs.getAllCategory();
	}
	@RequestMapping("/add_catagory.do")
	public HigherResponse<Object> addOneCategory(
			@RequestParam(required=true) String cName,//必须得到此值
			@RequestParam(required=true) Integer pId
			){
		Category category=new Category(null, pId, cName, 1, null, null, (byte)0);
		HigherResponse<Object> addOneCategory = cs.addOneCategory(category);
		System.out.println(addOneCategory);
		return addOneCategory;
	}
	
	
	@RequestMapping("/get_cname.do")
	public HigherResponse<String> getcNameById(
			@RequestParam(required=true)Integer cId){
		HigherResponse<String> cnameByCid = cs.getCnameByCid(cId);
		return cnameByCid;
	} 
	@RequestMapping("/get_deep_category.do")
	public HigherResponse<List<RCategory>> getChildCate(
			@RequestParam(required=true) Integer cId
			){
		return cs.getChildCategoryByCid(cId);
		
	}
	//查询所有的子节点
	@RequestMapping("/get_leaf_category.do")
	public HigherResponse<List<Category>> getLeafCate(){
		return cs.getLeafCate();
		
	}
	
	//门户查询类别
	@RequestMapping("/get_index_category.do")
	public HigherResponse<List<Category>> getCate(
			@RequestParam(required=true,defaultValue="0") Integer pId
			){
			return cs.getCategory(pId);
		
	}
	

}
