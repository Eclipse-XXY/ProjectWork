package com.xuxueya.atcrowdfunding.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuxueya.atcrowdfunding.bean.AccountCertType;
import com.xuxueya.atcrowdfunding.bean.Cert;
import com.xuxueya.atcrowdfunding.manager.service.CerttypeService;
import com.xuxueya.atcrowdfunding.util.AjaxResult;

/**
 * 表示用户的证件审核的间接表对应映射的实体类
 * 
 * @param
 * @author 徐雪娅
 */
@Controller
@RequestMapping("/certtype")
public class CertTypeController {
	@Autowired
	private CerttypeService certtypeService;
/**
 * 做完添加一条和删除一条之后需要让一些复选框的回显操作
 * @param
 * @author 徐雪娅
 */
	@RequestMapping("/index")
	public String toindex(Map<String, Object> map) {
		List<Cert> certList = certtypeService.queryAllCertType();
		map.put("certList", certList);
		List<AccountCertType> certAccttypeList =certtypeService.queryCertAccttype();
		map.put("certAccttypeList", certAccttypeList);
		return "certtype/index";
	}

	@ResponseBody
	@RequestMapping("/saveCerttype")
	public Object dosaveCertType(AccountCertType acctcertType) {
		AjaxResult result = new AjaxResult();
		try {
			int count = certtypeService.saveCertType(acctcertType);
			result.setSuccess(count == 1);

		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/deleteCerttype")
	public Object dodeleteCerttype(AccountCertType acctcertType) {
		AjaxResult result = new AjaxResult();
		try {
			int count = certtypeService.deleteCerttype(acctcertType);
			result.setSuccess(count == 1);

		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
		}
		return result;
	}

}
