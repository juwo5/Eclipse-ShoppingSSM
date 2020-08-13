package com.ju.dao;

import org.apache.ibatis.annotations.Param;

import com.ju.entity.Order;

public interface OrderDao {
	Order selOrderByUserIdAndOrderNo(
			@Param("userId") Integer userId,
			@Param("orderNo") Long orderNo);
	int updateOrderStatusById(Order order);
}
