package com.xuxueya.atcrowdfunding.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xuxueya.atcrowdfunding.bean.Member;
import com.xuxueya.atcrowdfunding.bean.User;
import com.xuxueya.atcrowdfunding.util.Const;



/**
 * 登录验证拦截器,不登录,资源是不允许访问的.
 * 默认适配器设计模式.
 * 	好处:只需要对感兴趣的方法进行实现.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{//implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("LoginInterceptor - preHandle.....");
		//白名单:不登录就允许访问
		Set<String> uris = new HashSet<String>();
		uris.add("/index.htm");
		uris.add("/login.htm");
		uris.add("/login.do");
		uris.add("/reg.htm");
		//uris.add("/member.htm");
		uris.add("/doUserLogin.do");
		uris.add("/doMemberLogin.do");
		//uris.add("/main.htm");
		
		//String requestURI = request.getRequestURI();
		String servletPath = request.getServletPath();
		//StringBuffer requestURL = request.getRequestURL();
		String contextPath = request.getContextPath();
		
		//System.out.println("requestURI="+requestURI); //    /atcrowdfunding-main/jquery/jquery-2.1.1.min.js
		System.out.println("servletPath="+servletPath);//     /jquery/jquery-2.1.1.min.js
		//System.out.println("requestURL="+requestURL); //    http://localhost/atcrowdfunding-main/jquery/jquery-2.1.1.min.js
		//System.out.println("contextPath="+contextPath); //  /atcrowdfunding-main
		
		if(uris.contains(servletPath)){
			return true ;
		}else{
			//true : 表示一定获取session对象.
				//如果之前服务器给客户端分配过session,则获取这个session,否则,创建一个新的.
			//false : 表示获取之前分配过的session 
				//如果之前服务器给客户端分配过session,则获取这个session,否则,返回null.
			HttpSession session = request.getSession(true);
			
			User user = (User)session.getAttribute(Const.LOGIN_USER);
			Member member = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			
			if(user == null && member == null){
				response.sendRedirect(contextPath+"/login.htm");
				return false;
			}else{
				return true ;
			}
		}
	}

//	@Override
//	public void postHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request,
//			HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		// TODO Auto-generated method stub
//
//	}

}
