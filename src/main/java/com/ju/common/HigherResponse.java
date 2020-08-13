package com.ju.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * 封装常用的响应对象
 * @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)过去使用
 * @JsonInclude(value=Include.NON_NULL)不为null的才返回。
 * @JsonInclude:为空的返回的json就不显示,
 *
 */
@Data
//@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonInclude(value=Include.NON_NULL)
public class HigherResponse<T> {
	private Integer status;//状态码
	
	private T data;//泛型类：放入是什么类
	
	private String msg;
	
	
	/**私有构造 不能new调用*/
	private HigherResponse() {
		
	}
	/**
	 * success:s   s,t,  s,m  s,t,m
	 * failed:s,m
	 */
	private HigherResponse(Integer status) {
		this.status=status;
	}
	private HigherResponse(Integer status,T t) {
		this.status=status;
		this.data=t;
	}
	private HigherResponse(Integer status,String msg) {
		this.status=status;
		this.msg=msg;
	}
	private HigherResponse(Integer status,String msg,T t) {
		this.status=status;
		this.msg=msg;
		this.data=t;
	}
	/**
	 * 对外方法
	 */
//	public static HigherResponse getResponseSuccess(){
//		return new HigherResponse(StatusUtil.SUCCESSSTATUS);//登录成功，没有消息
//	}
	
	@JsonIgnore
	public boolean isResponseSuccess() {

		return this.status == StatusUtil.SUCCESSSTATUS;
	}

	public static HigherResponse getResponseSuccess() {

		return new HigherResponse(StatusUtil.SUCCESSSTATUS);
	}

	public static HigherResponse getResponseSuccess(String msg) {

		return new HigherResponse(StatusUtil.SUCCESSSTATUS, msg);
	}

	public static <T> HigherResponse getResponseSuccess(T t) {

		return new HigherResponse(StatusUtil.SUCCESSSTATUS, t);
	}

	public static <T> HigherResponse getResponseSuccess(String msg, T t) {

		return new HigherResponse(StatusUtil.SUCCESSSTATUS, msg, t);
	}

	// Failed
	@JsonIgnore 
	public boolean isResponseFailed() {

		return this.status == StatusUtil.FAILESTATUS;
	}

	// 失败方法
	public static HigherResponse getResponseFailed() {

		return new HigherResponse(StatusUtil.FAILESTATUS);
	}

	public static HigherResponse getResponseFailed(String msg) {

		return new HigherResponse(StatusUtil.FAILESTATUS, msg);
	}

	

}
