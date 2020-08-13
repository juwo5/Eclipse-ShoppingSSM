package com.ju.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ju.common.HigherResponse;
import com.ju.entity.Shipping;

public interface ShippingService {
	public HigherResponse<String> addShipping(Integer userId,Shipping shipping);

	public HigherResponse<String> updateShippingByIdAndUserId(Integer userId, Shipping shipping);

	public HigherResponse<Shipping> selectShippingById(Integer userId, Integer shippingId);
	public HigherResponse<List<Shipping>> selectAll(Integer userId, Integer shippingId);
	public HigherResponse<List<Shipping>> selectAllByuserId(Integer userId);
	
	
	public HigherResponse<List<Shipping>> delShippingByShippingIds(Integer userId, String shippingIds);
}
