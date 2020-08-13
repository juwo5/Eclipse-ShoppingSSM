package com.ju.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ju.common.HigherResponse;
import com.ju.dao.CategoryDao;
import com.ju.entity.Category;
import com.ju.entity.RCategory;
import com.sun.org.apache.regexp.internal.recompile;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao cd;
	
	
	@Override
	public HigherResponse<Category> getAllCategory() {
		if(null==cd){
			return HigherResponse.getResponseSuccess("服务器出问题");
		}
		List<Category> allCategory = cd.getAllCategory();
		if(null==allCategory){
			return HigherResponse.getResponseFailed("商品不存在");
		}
		return HigherResponse.getResponseSuccess(allCategory);
	}


	@Override
	public HigherResponse<Object> addOneCategory(Category c) {
		if(null==c){
			return HigherResponse.getResponseFailed("添加失败");
		}
		Boolean addCategory = cd.addCategory(c);
		if(false==addCategory){
			return HigherResponse.getResponseFailed("添加失败");
		}
		return HigherResponse.getResponseSuccess("添加成功");
	}


	@Override
	public HigherResponse<String> getCnameByCid(Integer cId) {
		if(null==cId){
			return HigherResponse.getResponseFailed("服务器异常");
		}
		String cateNameByCid = cd.getCateNameByCid(cId);
		if(null==cateNameByCid){
			return HigherResponse.getResponseFailed("名不存在");
		}
		return HigherResponse.getResponseSuccess(cateNameByCid);
	}


	@Override
	public HigherResponse<List<RCategory>> getChildCategoryByCid(Integer cId) {
		if(null==cId){
			return HigherResponse.getResponseFailed("服务器异常");
		}
		List<RCategory> childCateByCid = cd.getChildCateByCid(cId);
		if(null==childCateByCid){
			return HigherResponse.getResponseFailed("没有子类信息");
		}
		return HigherResponse.getResponseSuccess(childCateByCid);
	}


	@Override
	public HigherResponse<List<Category>> getLeafCate() {
		List<Category> leafCate = cd.getLeafCate();
		if(null==leafCate){
			return HigherResponse.getResponseFailed("查询失败");
		}
		return HigherResponse.getResponseSuccess(leafCate);
	}


	@Override
	public HigherResponse<List<Category>> getCategory(Integer pId) {
		if(null==pId){
			return HigherResponse.getResponseFailed("服务器异常");
		}
		if(0==pId){
			List<Category> rootCate = cd.getRootCate(0);
			if(null==rootCate){
				return HigherResponse.getResponseFailed("没有读取到任何类别");
			}
			return HigherResponse.getResponseSuccess(rootCate);
		}
		List<Category> rootCate = cd.getRootCate(pId);
		
		return HigherResponse.getResponseSuccess(rootCate);
	}

	

}
