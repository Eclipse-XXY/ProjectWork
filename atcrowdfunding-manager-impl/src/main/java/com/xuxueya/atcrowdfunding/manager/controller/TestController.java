package com.xuxueya.atcrowdfunding.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuxueya.atcrowdfunding.util.DesUtil;



@Controller
@RequestMapping("/test")
public class TestController {

	@ResponseBody
	@RequestMapping("/act")
	public Object act( String p ) throws Exception {
		
		String val = DesUtil.decrypt(p, "abcdefghijklmnopquvwxyz");
		
		System.out.println("val=====>>>>>>>"+val);
		
		return val;
	}

	
}
