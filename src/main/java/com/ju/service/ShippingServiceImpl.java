package com.ju.service;

import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ju.common.HigherResponse;
import com.ju.dao.ShippingDao;
import com.ju.entity.Shipping;

import sun.net.www.content.text.plain;
@Service
public class ShippingServiceImpl implements ShippingService{
	@Autowired
	private ShippingDao shippingDao;
	@Override
	public HigherResponse<String> addShipping(Integer userId, Shipping shipping) {
		if(StringUtils.isEmpty(shipping))
			return HigherResponse.getResponseFailed("地址不存在");
		if(StringUtils.isEmpty(userId))
			return HigherResponse.getResponseFailed("用户id不存在");
		shipping.setUserId(userId);
		int num=shippingDao.insert(shipping);
		if(num>0){
			return HigherResponse.getResponseSuccess("收货地址添加成功");
		}
		
		return HigherResponse.getResponseFailed("地址添加失败");
	}
	@Override
	public HigherResponse<String> updateShippingByIdAndUserId(Integer userId, Shipping shipping) {
		shipping.setUserId(userId);
		int num=shippingDao.updateShippingByIdAndUserId(shipping);
		if(num>0){
			return HigherResponse.getResponseSuccess("修改成功");
		}
		
		return HigherResponse.getResponseFailed("地址修改失败");
	}
	@Override
	public HigherResponse<Shipping> selectShippingById(Integer userId, Integer shippingId) {
		
		Shipping selectShippingById = shippingDao.selectShippingById(userId, shippingId);
		if(StringUtils.isEmpty(selectShippingById))
			return HigherResponse.getResponseFailed("查询失败");
		return HigherResponse.getResponseSuccess(selectShippingById);
	}
	
	
	@Override
	public HigherResponse<List<Shipping>> selectAll(Integer userId, Integer shippingId) {
		
		List<Shipping> selectAll = shippingDao.selectAll(userId, shippingId);
		if(selectAll.size()==0)
			return HigherResponse.getResponseFailed("查询失败");
		
		return HigherResponse.getResponseSuccess(selectAll);
	}
	@Override
	public HigherResponse<List<Shipping>> selectAllByuserId(Integer userId) {
		List<Shipping> selectAllByuserId = shippingDao.selectAllByuserId(userId);
		if(selectAllByuserId.size()==0)
			return HigherResponse.getResponseFailed("查询失败");
		return HigherResponse.getResponseSuccess(selectAllByuserId);
	}
	
	
	
	
	
	
	@Override
	public HigherResponse<List<Shipping>> delShippingByShippingIds(Integer userId, String shippingIds) {
		String[] split = shippingIds.split(",");  List<String> shippingIdList = Arrays.asList(split);
		//等于新的jia包<String> shippingIdList=Splitter.on(",").splitToList(shippingIds)
		if(shippingIdList.size()==0)
			return  HigherResponse.getResponseFailed("删除失败");
		int num = shippingDao.delShippingByShippingIds(userId, shippingIdList);
		if(num==0)
			return   HigherResponse.getResponseFailed("操作失败");
		//删除成功，查询全部
		//int parseInt = Integer.parseInt(shippingIds);//string: "6,7"不能变为Integer
		/*
		String string = Arrays.toString(split);
		int parseInt = Integer.parseInt(string);
		System.out.println("数组："+Arrays.toString(split));
		System.out.println("int："+parseInt);
		*/
		List<Shipping> selectAllByuserId = shippingDao.selectAllByuserId(userId);
		return HigherResponse.getResponseSuccess(selectAllByuserId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
