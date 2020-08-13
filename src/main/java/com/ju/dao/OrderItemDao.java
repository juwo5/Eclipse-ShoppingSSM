package com.ju.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ju.entity.OrderItem;

public interface OrderItemDao {
	List<OrderItem> queryOrderItemsByUserIdAndOrder(
			@Param("userId") Integer userId,
			@Param("orderNo") Long orderNo);

	
	
	
	
	
}
