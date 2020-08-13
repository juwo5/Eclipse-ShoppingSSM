package com.ju.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ju.entity.Shipping;

public interface ShippingDao {
	//添加一个地址
	int insert(Shipping shipping);

	int updateShippingByIdAndUserId(Shipping shipping);
	Shipping selectShippingById(@Param("userId") Integer userId,@Param("shippingId")Integer shippingId);
	List<Shipping> selectAll(@Param("userId") Integer userId,@Param("shippingId")Integer shippingId);
	List<Shipping> selectAllByuserId(@Param("userId") Integer userId);

	//删除
	int delShippingByShippingIds(@Param("userId") Integer userId,@Param("shippingIdList") List<String> shippingIdList);

	
	
	
	
}
