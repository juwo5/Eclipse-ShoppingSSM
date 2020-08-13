package com.ju.service;

import javax.servlet.http.HttpServletRequest;

import com.ju.common.HigherResponse;
import com.ju.entity.CartVO;

public interface CartService {
	//查询（根据用户id）
	//public HigherResponse<CartVO> queryCartByUserId(Integer userId);
	public HigherResponse<CartVO> queryCartByUserId(HttpServletRequest request);
	
	//添加(根据商品id和商品数量)//必须保证是登录
	public HigherResponse<CartVO> addOneProductToCart(Integer proId,Integer count,HttpServletRequest request);

	//从购物车内删除商品
	public HigherResponse<CartVO> removeOneProduct(Integer proId,HttpServletRequest request);
	//更新修改商品数量
	public HigherResponse<CartVO> updateOneProductFromCart(Integer proId,Integer count,HttpServletRequest request);
}
