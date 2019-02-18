package com.emotte.order.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;

/**
 * TODO Cookie工具类
 * @author Liucx
 * @createTime 2016年3月29日 上午9:59:56
 * @className CookieUtil.java
 * @version 
 */
public final class CookieReaderUtil {
	
	
	/**
	   * 根据Cookie名称得到Cookie对象，不存在该对象则返回Null
	   * 
	   * @param request
	   * @param name
	   * @return
	   */
	  public static Cookie getCookie(HttpServletRequest request, String name) {
	    Cookie[] cookies = request.getCookies();
	    if (null == cookies || null == name || name.length() == 0) {
	      return null;
	    }
	    Cookie cookie = null;
	    for (Cookie c : cookies) {
	      if (name.equals(c.getName())) {
	        cookie = c;
	        break;
	      }
	    }
	    return cookie;
	  }
	  
	  /**
	     *
	     * @desc 根据Cookie名称得到Cookie的值，没有返回Null
	     * @param request
	     * @param name
	     * @return
	     */
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		if (cookie != null) {
			return cookie.getValue();
		} else {
			return null;
		}
	}

	  /**
	   * 读取登录人的Cookie信息
	   * @TODO TODO
	   * @author Administrator
	   * @createTime 2016年3月29日 上午11:30:33
	   * @param request
	   * @return LoginCookieInfo
	   * @throws UnsupportedEncodingException
	   */
	  public static LoginCookieInfo cookieReader(HttpServletRequest request) throws UnsupportedEncodingException{
		  String cookie = getCookieValue(request, Constants.LOGIN_COOKIE_COOKIENAME);
		  String decoderCookie = URLDecoder.decode(cookie, "UTF-8");
		  LoginCookieInfo loginCookieInfo = JSON.parseObject(decoderCookie, LoginCookieInfo.class);
		  return loginCookieInfo;
	  }
	  
	  public static void main(String[] args) {
	}
}
