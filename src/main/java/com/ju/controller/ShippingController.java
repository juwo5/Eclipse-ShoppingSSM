package com.ju.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ju.common.HigherResponse;
import com.ju.dao.ShippingDao;
import com.ju.entity.Shipping;
import com.ju.entity.User;
import com.ju.service.ShippingService;
import com.sun.org.apache.regexp.internal.recompile;
@RestController
@RequestMapping("/shipping/")
public class ShippingController {
	@Autowired
	private ShippingService shippingService;
	
	@RequestMapping("addShipping")
	public HigherResponse<String> addShipping(HttpSession session,Shipping shipping){
		User user = (User) session.getAttribute("user");
		if(null==user)
			return HigherResponse.getResponseFailed("没有登录");
		HigherResponse<String> addShipping = shippingService.addShipping(user.getId(), shipping);
		return addShipping;
		
	}
	@RequestMapping("updateShipping")
	public HigherResponse<String> updateShippingByIdAndUserId(HttpSession session,Shipping shipping){
		User user = (User) session.getAttribute("user");
		if(null==user)
			return HigherResponse.getResponseFailed("没有登录");
		HigherResponse<String> updateShippingByIdAndUserId = 
				shippingService.updateShippingByIdAndUserId(user.getId(),shipping);
		return updateShippingByIdAndUserId;
	}
	//查询个地址
	
	@RequestMapping("selectShippingById")
	public HigherResponse<Shipping> selectShippingById(HttpSession session,Integer shippingId){
		User user = (User) session.getAttribute("user");
		if(null==user)
			return HigherResponse.getResponseFailed("没有登录");
		HigherResponse<Shipping> selectShippingById = shippingService.selectShippingById(user.getId(),shippingId);
		System.out.println("查询一个地址"+selectShippingById);
		return selectShippingById;
	}
	@RequestMapping("selectAll")
	public HigherResponse<List<Shipping>> selectAll(HttpSession session,Integer shippingId){
		User user = (User) session.getAttribute("user");
		if(null==user)
			return HigherResponse.getResponseFailed("没有登录");
		HigherResponse<List<Shipping>> selectAll = shippingService.selectAll(user.getId(),shippingId);
		return selectAll;
	}
	@RequestMapping("selectAllByuserId")
	public HigherResponse<List<Shipping>> selectAllByuserId(HttpSession session){
		User user = (User) session.getAttribute("user");
		if(null==user)
			return HigherResponse.getResponseFailed("没有登录");
		HigherResponse<List<Shipping>> selectAllByuserId = shippingService.selectAllByuserId(user.getId());
		return selectAllByuserId;
	}
	
	
	
	//可以多选删除(得到选中的所有商品id,用split()分割
	@RequestMapping("delete")
	public HigherResponse<List<Shipping>>  delete(HttpSession session,String shippingIds){
		User user = (User) session.getAttribute("user");
		if(StringUtils.isEmpty(user))
			return HigherResponse.getResponseFailed("没有登录");
		//删除
		HigherResponse<List<Shipping>> delShippingByShippingIds = shippingService.delShippingByShippingIds(user.getId(), shippingIds);
		//查询
		HigherResponse<List<Shipping>> selectAllByuserId = shippingService.selectAllByuserId(user.getId());
		return selectAllByuserId;
	}
	
	
	
	
}
