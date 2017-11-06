package com.xuxueya.atcrowdfunding.listener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xuxueya.atcrowdfunding.bean.Permission;
import com.xuxueya.atcrowdfunding.manager.service.PermissionService;
import com.xuxueya.atcrowdfunding.util.Const;

/**
 * 在服务器启动过程中将request.getContextPath()路径存放到application域中.
 * @author zhangyu
 * 
 */
public class ServerStartUpListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//将request.getContextPath()路径从application域删除
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//监听器一的作用：将request.getContextPath()路径存放到application域中.
		ServletContext application = arg0.getServletContext();
		String contextPath = application.getContextPath();
		application.setAttribute("APP_PATH", contextPath);
		
		//监听器二的作用把所有的系统的拦截都放在listener监听器当中
		//因为监听器属于Tomcat不属于框架所以需要手动创建iOS容器
		WebApplicationContext ioc = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
	PermissionService permissionService = ioc.getBean(PermissionService.class);
	 //获取全部的许可的URI  要是不在里面的全部拦截
	 List<Permission> queryAllPermission = permissionService.queryAllPermission();
	 Set<String> allUris=new HashSet<>();
	for (Permission p : queryAllPermission) {
		allUris.add("/"+p.getUrl());
	}
	application.setAttribute(Const.SYSTEM_ALL_PERMISSIONURL, allUris);
	}
	
	
}
