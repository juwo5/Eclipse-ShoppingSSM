package com.ju.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ju.common.HigherResponse;
import com.ju.entity.Product;
import com.sun.swing.internal.plaf.basic.resources.basic;

public interface ProductService {
	//分页
	public HigherResponse<Object> getPageBeanProduct(Integer pageNum,Integer pageSize);
	
	//模糊查询
	public HigherResponse<List<Product>> searchProByPage(Product product,Integer pageNum,Integer pageSize);
	//修复下架
	public HigherResponse<Boolean> updateProStatus(Product product); 
	
	//添加商品product
	public HigherResponse<Boolean> addOneProduct(Product product,MultipartFile file)throws IllegalStateException, IOException;
	//门户接口根据id和商品名查询商品
	//HigherResponse<PageInfo<Product>>= HigherResponse<Object> 
	public HigherResponse<Object> queryProByCIdAndName(Integer pageNum,Integer pageSize,Product product);
	
	public HigherResponse<Product> queryProInfoByPId (Integer pId);
	
	
	
}
