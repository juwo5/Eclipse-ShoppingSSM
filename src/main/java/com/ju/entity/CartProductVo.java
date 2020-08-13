package com.ju.entity;

import java.math.BigDecimal;

import lombok.Data;
/**购物车：用户信息，商品信息，
 * 
 * 没有数据库表：需要视图对象，多表混杂。sql表关联。
 * 
 * */

public class CartProductVo {
	private Integer id;//购物车表id
	private Integer userId;//用户id
	private Integer productId;//产品id
	private Integer quantity;//产品数量
	private String productName;//产品名
	
	private String productSubtitle;// 商品的副标题//产品表
	private String productMainImage;
	private BigDecimal productPrice;//产品价格
	// 商品状态
	private Integer productStatus;
	// 总价
	private BigDecimal productTotalPrice;//产品总价
	// 商品库存
	private Integer productStock;
	private Integer productChecked;//是否选择
	//
	private String limitQuantity;
	
	public CartProductVo(Integer id, Integer userId, Integer productId, Integer quantity, String productName,
			String productSubtitle, String productMainImage, BigDecimal productPrice, Integer productStatus,
			BigDecimal productTotalPrice, Integer productStock, Integer productChecked/*, String limitQuantity*/) {
		super();
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
		this.productName = productName;
		this.productSubtitle = productSubtitle;
		this.productMainImage = productMainImage;
		//单机
		this.productPrice = productPrice;
		this.productStatus = productStatus;
		//总价=单价*数量BigDecimal
		this.productTotalPrice = productTotalPrice;//this.getProductPrice().add(new BigDecimal(this.getQuantity()));
		this.productStock = productStock;
		this.productChecked = productChecked;
		//this.limitQuantity = limitQuantity;
	}
	public CartProductVo() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductSubtitle() {
		return productSubtitle;
	}
	public void setProductSubtitle(String productSubtitle) {
		this.productSubtitle = productSubtitle;
	}
	public String getProductMainImage() {
		return productMainImage;
	}
	public void setProductMainImage(String productMainImage) {
		this.productMainImage = productMainImage;
	}
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	public Integer getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}
	public BigDecimal getProductTotalPrice() {
		return this.getProductPrice().multiply(new BigDecimal(this.getQuantity()));
	}
	public void setProductTotalPrice(BigDecimal productTotalPrice) {
		this.productTotalPrice = productTotalPrice;
	}
	public Integer getProductStock() {
		return productStock;
	}
	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}
	public Integer getProductChecked() {
		return productChecked;
	}
	public void setProductChecked(Integer productChecked) {
		this.productChecked = productChecked;
	}
	public String getLimitQuantity() {
		return limitQuantity;
	}
	public void setLimitQuantity(String limitQuantity) {
		this.limitQuantity = limitQuantity;
	}
	
	
	

}
