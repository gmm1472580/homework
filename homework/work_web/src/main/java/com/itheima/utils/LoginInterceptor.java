package com.itheima.utils;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginInterceptor implements HandlerInterceptor {
    /*HandlerInterceptor中有三个方法
    * 1.执行操作之前执行的方法，预处理方法
    * default boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return true;
	}
    *
    * 2.执行controller里面的方法后执行
    * default void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
	}
	* 3.执行所有的功能之后，即页面执行完后执行的方法
	* default void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
	}.
	*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        //instanceof:判断handler 是不是DefaultServletHttpRequestHandler的子类
        if (handler instanceof DefaultServletHttpRequestHandler){
            return true;
        }
        //当session不是空的时候，让其访问
        HttpSession session = request.getSession();
        if (session.getAttribute("user")!=null){
            return true;
        }
        response.sendRedirect(request.getContextPath()+"/index.jsp");
        return false;
    }
}
