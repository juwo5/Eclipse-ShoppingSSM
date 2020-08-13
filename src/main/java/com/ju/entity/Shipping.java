package com.ju.entity;

import java.util.Date;

import lombok.Data;
@Data
public class Shipping {
	private Integer id;
	private Integer userId;
	private String receiverName;
	private String receiverPhone;
	private String receiverMobile;
	private String receiverProvince;
	private String receiverCity;
	private String receiverCistrict;
	private String receiverAddress;
	private String receiverZip;
	private Date createTime;
	private Date updateTime;
	public Shipping(Integer id, Integer userId, String receiverName, String receiverPhone, String receiverMobile,
			String receiverProvince, String receiverCity, String receiverCistrict, String receiverAddress,
			String receiverZip, Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.receiverName = receiverName;
		this.receiverPhone = receiverPhone;
		this.receiverMobile = receiverMobile;
		this.receiverProvince = receiverProvince;
		this.receiverCity = receiverCity;
		this.receiverCistrict = receiverCistrict;
		this.receiverAddress = receiverAddress;
		this.receiverZip = receiverZip;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	public Shipping() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
