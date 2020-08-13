package com.ju.entity;

import java.math.BigDecimal;
import java.util.List;

import com.ju.common.StatusUtil;
import com.ju.utils.UUIDUtils;

import lombok.Data;
/**
 * 购物车的总项
 *
 */
@Data
public class CartVO {
	private List<CartProductVo> cartProductVolist;//集合类
	private BigDecimal cartTotalPrice;//总价
	private Boolean allChecked;//总的是否，全选
	private String imageHost = StatusUtil.IMG_HOST;// 服务器图像路径名称
	
	
	
	public CartVO() {
		super();
	}
	public CartVO(List<CartProductVo> cartProductVolist, BigDecimal cartTotalPrice, Boolean allChecked) {
		super();
		this.cartProductVolist = cartProductVolist;
		this.cartTotalPrice = cartTotalPrice;
		this.allChecked = allChecked;
		this.imageHost =imageHost;
	}
	
	

}
