package com.ju.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ju.common.HigherResponse;
import com.ju.common.JSONData;
import com.ju.entity.User;
import com.ju.service.PayService;

@RestController
@RequestMapping("/pay/")
public class PayContoller {
	@Autowired
	private PayService payService;
	
	@RequestMapping(value="pay",method=RequestMethod.POST)
	public JSONData pay(HttpSession session,Long orderNo,HttpServletRequest request){
		//1.判断用户是否登录
		User user = (User) session.getAttribute("user");
		if(null==user) 
			return JSONData.buildError("没有登录");
		//2.支付环境service
		String path = request.getSession().getServletContext().getRealPath("upload");
		JSONData data = payService.pay(user.getId(), orderNo, path);
		
		return data;
	}
	//callback请求
	@RequestMapping("callback")
	public JSONData payCallBack(HttpSession session,HttpServletRequest request){
		Map<String,String> map = new HashMap<String,String>();
		Map<String, String[]> parameterMap = request.getParameterMap();
		Set<String> keySet = parameterMap.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key =  iterator.next();
			String[] vals = parameterMap.get(key);
			String val_str="";
			for (int i = 0; i < vals.length; i++) {
				val_str = (i==vals.length-1)?val_str+vals[i]:val_str+vals[i]+",";//最后一个不加，
			}
			
			map.put(key, val_str);
		}
		System.out.println("支付宝签名"+map.get("sign"));
		System.out.println("交易状态"+map.get("trade_status"));
		//回调的正确性
		map.remove("sign_type");
		boolean flag=AlipaySignature.rsaCheckV2(map,Configs.getAlipayPublicKey(),"utf-8",Configs.getSignType);
		if(!flag)
			return JSONData.buildError("非法请求，验证不通关");
		//修改支付信息
		User user = (User) session.getAttribute("user");
		JSONData data = payService.callback(map, user.getId());
		return data;
		
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
