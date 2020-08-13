package com.ju.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ju.entity.CartProductVo;

public interface CartDao {
	//查询该用户所有商品信息
	public List<CartProductVo> getCartProductByUserId(Integer userId); 
	//添加购物车：向cart数据库内添加数据
	public Boolean addOneCartProduct(CartProductVo cartProductVo);
	//删除购物车内的商品:用户id和商品id
	public Boolean removeOneProduct(@Param("userId")Integer userId,@Param("proId") Integer proId);
	//更新修改商品
	public Boolean updateProFromCart(@Param("userId")Integer userId,@Param("proId") Integer proId,@Param("count") Integer count);
	
	//订单业务：
	/*1.登录
	2.
	*/
	
}
