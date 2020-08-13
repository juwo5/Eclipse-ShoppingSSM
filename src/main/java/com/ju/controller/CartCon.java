package com.ju.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ju.common.HigherResponse;
import com.ju.entity.CartVO;
import com.ju.service.CartService;

@RestController
@RequestMapping("/portal/cart")
public class CartCon {
	@Autowired
	private CartService cs;
	
	
	@RequestMapping("/list.do")
	/*public HigherResponse<CartVO> queryCartByUserId(Integer userId){
		return cs.queryCartByUserId(userId);
	}*/
	public HigherResponse<CartVO> queryCartByUserId(HttpServletRequest request){
		return cs.queryCartByUserId(request);
	}
	//添加商品到购物车
	@RequestMapping("/add.do")
	public HigherResponse<CartVO> addOneProductToCart(Integer proId, Integer count,HttpServletRequest request){
		return cs.addOneProductToCart(proId, count, request);
	}
	@RequestMapping("/delete_product.do")
	public HigherResponse<CartVO> deleteProductFromCart(Integer proId,HttpServletRequest request){
		return cs.removeOneProduct(proId, request);
	}
	@RequestMapping("/update_product.do")
	public HigherResponse<CartVO> updateOneProductFromCart(Integer proId, Integer count,HttpServletRequest request){
		return cs.updateOneProductFromCart(proId, count, request);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
