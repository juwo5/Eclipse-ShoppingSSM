package com.ju.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/*
 * 高服用方法2：网路传输需要序列化
 * 返回值：不为null才返回
 */
@JsonInclude(value=Include.NON_NULL)
public class JSONData<T>  implements Serializable{
	private Integer code;//状态码：0成功，1处理，-1失败
	private String msg;//提示或描述信息
	private T data;//数据
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	public JSONData(T data){
		this.data=data;
	}
	public JSONData(Integer code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	//成功后：使用静态方法：
	public static JSONData buildSuccess(){
		return new JSONData(0,null,null);
	}
	public static JSONData buildSuccess(String msg){
		return new JSONData(0,null,null);
	}
	public static <T> JSONData buildSuccess(T data){
		return new JSONData(0,null,null);
	}
	public static <T> JSONData buildSuccess(T data,String msg){
		return new JSONData(0,null,null);
	}
	//失败
	public static JSONData buildError(){
		return new JSONData(-1,null,null);
	}
	public static JSONData buildError(String msg){
		return new JSONData(-1,null,null);
	}
		//失败可以自己设计状态码
	public static JSONData buildError(Integer code,String msg){
		return new JSONData(code,null,null);
	}
}
