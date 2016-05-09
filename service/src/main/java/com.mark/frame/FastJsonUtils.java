package com.mark.frame;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class FastJsonUtils {
	public static <T> T fromJson(String str,Class<T> clazz){
		return JSON.parseObject(str, clazz);
	}
	
	public static <T> T fromJson(String json,TypeReference<T> typeReference){
		return JSON.parseObject(json, typeReference);
	}
	public static <T> String toJson(T source){
		
		return JSON.toJSONString(source);
	}
	

}