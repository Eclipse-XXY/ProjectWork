package com.xuxueya.atcrowdfunding.interceptor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xuxueya.atcrowdfunding.bean.Permission;
import com.xuxueya.atcrowdfunding.manager.service.PermissionService;
/**
 * 设置权限拦截器拦截器放行的部分可以访问
 * @param
 * @author 徐雪娅
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
	//仔细看放入的位置不要放到函数里面去了
	@Autowired
	private PermissionService permissionService;

public boolean preHandle(HttpServletRequest request,
		HttpServletResponse response, Object handler) throws Exception {
	System.out.println("AuthInterceptor - preHandle.....");
	 //1获取请求的ServletPath
	 String servletPath = request.getServletPath();
	 System.out.println("servletPath"+servletPath);
	Set<String> allUris = (Set<String>) request.getSession().getServletContext().getAttribute("allUris");

	 //获取用户的URI判断用户可以对那些权限进行访问可以访问的放行不可以访问的拦截
	 //用户可以访问的URL已经封装在dispatcherContoller里面了
HttpSession session = request.getSession(false);
Set<String> allUserURis= (Set<String>)session.getAttribute("allUserPermissionURis");
	if (allUris.contains(servletPath)) {
		//在所有的权限当中在区分哪些是用户可以享受的权限哪些不是用户可以享受的权限
		if (allUserURis.contains(servletPath)) {
//			判断请求是不是在用户可以访问的权限里面
			return true;
		}
		else {
			response.sendRedirect(request.getContextPath()+"/errorauth.htm");
			return false;
		}
	}else {
		//其他的我放行因为需要拦截的都已经放在了数据库当中或者不是系统的就回报404
		return true;
	}

}
}
