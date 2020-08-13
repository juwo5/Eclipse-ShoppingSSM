package com.ju.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ju.common.HigherResponse;
import com.ju.common.JSONData;

public interface PayService {
	public JSONData pay(Integer userId,Long orderNo,String path);
	public JSONData callback(Map<String, String> params,Integer userId);

}
