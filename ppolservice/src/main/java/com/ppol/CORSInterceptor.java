/**
 * 
 */
package com.ppol;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author rhogue
 */
public class CORSInterceptor extends HandlerInterceptorAdapter
{
	 @Override
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	 {
//		 response.addHeader("Access-Control-Allow-Origin", "/*");

		 return true;
	 }
} 