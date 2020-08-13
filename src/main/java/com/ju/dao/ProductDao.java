package com.ju.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ju.entity.Product;

public interface ProductDao {
	//商品列表分页查询
	public List<Product> pageQueryPro();
	//商品id或商品名 页面模糊查询、类参得集合
	public List<Product> searchProByPage(Product product);
	
	//修改
	public Boolean updateProStatus(Product product); 
	
	//添加
	public Boolean addOneProduct(Product product); 
	
	//门口接
	//根据类别id和关键字查询商品
	public List<Product> queryProByCIdAndKeyWord(Product product);
	//根据商品id查询商品信息
	public Product queryProInfoByPId(Integer pId);
	
	
	//购物车：根据商品id,修改库存
	//public Boolean updateStockFromProduct(@Param("pId")Integer proId,@Param("stock")Integer stock);
	//根据商品的ID修改商品的库存
	public Boolean updateStockFromProduct(@Param("proId")Integer proId,@Param("num")Integer num);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
