package com.xuxueya.atcrowdfunding.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**
 * 基于接口的注入方式
 * @param
 * @author 徐雪娅
 */
@Component
public class ApplicationContextUilts implements ApplicationContextAware {
public  static ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ApplicationContextUilts.applicationContext=applicationContext;
	}

}
