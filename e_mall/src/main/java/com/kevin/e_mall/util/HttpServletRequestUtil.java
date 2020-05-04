package com.kevin.e_mall.util;

import javax.servlet.http.HttpServletRequest;

//将http请求转换相应格式-工具
public class HttpServletRequestUtil {
	public static int getInt(HttpServletRequest request,String key) {
		try {
			return Integer.decode(request.getParameter(key));
		}catch(Exception e){
			return -1;
		}
	}
	
	public static long getLong(HttpServletRequest request,String key) {
		try {
			return Long.valueOf(request.getParameter(key));
		}catch(Exception e){
			return -1;
		}
	}
	
	public static Double getDouble(HttpServletRequest request,String key) {
		try {
			return Double.valueOf(request.getParameter(key));
		}catch(Exception e){
			return -1d;
		}
	}
	
	public static boolean getBoolean(HttpServletRequest request,String key) {
		try {
			return Boolean.valueOf(request.getParameter(key));
		}catch(Exception e){
			return false;
		}
	}
	
	public static String getString(HttpServletRequest request,String key) {
		try {
			String res = request.getParameter(key);
			if(res != null) {
				res = res.trim();
			}
			if("".equals(res)) {
				res = null;
			}
			return res;
		}catch(Exception e){
			return null;
		}
	}
}
