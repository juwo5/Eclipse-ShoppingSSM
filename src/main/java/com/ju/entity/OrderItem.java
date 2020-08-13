package com.ju.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
@Data
public class OrderItem {
	private Integer id;
	private Integer userId;
	private Long orderNo;
	private Integer productId;
	private String productName;
	private String productImage;
	private BigDecimal currentUnitPrice;
	private Integer quantity;
	private BigDecimal totalPrice;
	private Date creatTime;
	private Date updateTime;
	public OrderItem(Integer id, Integer userId, Long orderNo, Integer productId, String productName,
			String productImage, BigDecimal currentUnitPrice, Integer quantity, BigDecimal totalPrice, Date creatTime,
			Date updateTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.orderNo = orderNo;
		this.productId = productId;
		this.productName = productName;
		this.productImage = productImage;
		this.currentUnitPrice = currentUnitPrice;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.creatTime = creatTime;
		this.updateTime = updateTime;
	}
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
