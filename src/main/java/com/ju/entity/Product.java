package com.ju.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class Product {
	private Integer id;
	private Integer category_id;
	private String name;
	private String detail;
	private String subtitle;
	private String main_image;//图片
	private String sub_images;//
	private BigDecimal price;//double类型出精度的损失
	private Integer stock;
	private Byte status;
	@JsonFormat(pattern="yyyy年MM月dd日")
	private Date create_time;//util
	@JsonFormat(pattern="yyyy年MM月dd日hh:mm:ss")
	private Date update_time;
	public Product(Integer id, Integer category_id, String name, String detail, String subtitle, String main_image,
			String sub_images, BigDecimal price, Integer stock, Byte status, Date create_time, Date update_time) {
		super();
		this.id = id;
		this.category_id = category_id;
		this.name = name;
		this.detail = detail;
		this.subtitle = subtitle;
		this.main_image = main_image;
		this.sub_images = sub_images;
		this.price = price;
		this.stock = stock;
		this.status = status;
		this.create_time = create_time;
		this.update_time = update_time;
	}
	public Product() {
		super();
	}
	

}
