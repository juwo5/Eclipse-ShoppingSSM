package com.ju.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ju.entity.Category;
import com.ju.entity.RCategory;

public interface CategoryDao {
	List<Category> getAllCategory();
	//添加子节点
	Boolean addCategory(Category category);
	//根据cid 查询商品名称
	String getCateNameByCid(Integer cId);
	//根据类别的cId去查询子节点
	List<RCategory> getChildCateByCid(Integer cId);
	//查询所有的叶子节点 is_leaf=0
	List<Category> getLeafCate();
	
	/*//查询一级类别节点
	List<Category> getRootCate();
	//查询二级类别节点(对应的一级类别)
	List<Category> getSecCate(Integer pId);*/
	//查询类别
	List<Category> getRootCate(@Param("pid")Integer pId);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
