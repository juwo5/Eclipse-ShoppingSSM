package com.ju.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
@Data
public class Order {
	private Integer id;
	private Long orderNo;
	private Integer userId;
	private BigDecimal payment;
	private Integer paymentType;
	private Integer status;
	private Integer postage;
	private Date paymentTime;
	private Date sendTime;
	private Date closeTime;
	private Date endTime;
	private Date createTime;
	private Date updateTime;
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Integer id, Long orderNo, Integer userId, BigDecimal payment, Integer paymentType, Integer status,
			Integer postage, Date paymentTime, Date sendTime, Date closeTime, Date endTime, Date createTime,
			Date updateTime) {
		super();
		this.id = id;
		this.orderNo = orderNo;
		this.userId = userId;
		this.payment = payment;
		this.paymentType = paymentType;
		this.status = status;
		this.postage = postage;
		this.paymentTime = paymentTime;
		this.sendTime = sendTime;
		this.closeTime = closeTime;
		this.endTime = endTime;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	
}
