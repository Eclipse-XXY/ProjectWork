package com.xuxueya.atcrowdfunding.manager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuxueya.atcrowdfunding.bean.Cert;
import com.xuxueya.atcrowdfunding.manager.service.CertService;
import com.xuxueya.atcrowdfunding.util.AjaxResult;
import com.xuxueya.atcrowdfunding.util.Datas;
import com.xuxueya.atcrowdfunding.util.Page;
import com.xuxueya.atcrowdfunding.util.StringUtil;
@RequestMapping("/cert")
@Controller
public class CertController {
	@Autowired
private CertService certService;
	@RequestMapping("/index")
	public String toIndex() {
	return "cert/index";	
	}
	@RequestMapping("/add")
	public String toAdd() {
		return "cert/add";	
	}
	@RequestMapping("/edit")
	public String toedit(int id,Map<String, Object> map) {
		Cert cert=certService.queryCert(id);
		map.put("cert", cert);
		return "cert/edit";	
	}

	@ResponseBody
	@RequestMapping("/queryPage")
	public Object doqueryPage(@RequestParam(value="pageNo",defaultValue="1",required=false)Integer pageNo,
	@RequestParam(value="pageSize",defaultValue="3",required=false)Integer pageSize,
     String pagetext ) {
		AjaxResult result=new AjaxResult();
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("pageNo", pageNo);
		paramMap.put("pageSize", pageSize);
		if (StringUtil.isNotEmpty(pagetext)) {
			pagetext=pagetext.replaceAll("%", "\\\\%");
			paramMap.put("pagetext", pagetext);	
		}
		try {
			Page<Cert> page=certService.queryPage(paramMap);
		
			result.setPage(page);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setErrorMessage("无法进行分页");
			result.setSuccess(false);
		}
		return result;	
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Object update(Integer id,Cert cert){
		AjaxResult result=new AjaxResult();
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("cert", cert);
		try {
			int count=certService.update(paramMap);
			if (count>0) {
			
				result.setSuccess(true);
			}else {
				result.setErrorMessage("无法进行分页");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			
		}
		return result;	
	}
	@ResponseBody
	@RequestMapping("/saveCert")
	public Object dosaveCert(Cert cert) {
		AjaxResult result=new AjaxResult();
		try {
			int count=certService.saveCert(cert);
			if (count>0) {
				result.setSuccess(true);
			}else {
				result.setErrorMessage("保存失败");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			
		}
		return result;	
	}
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete(Integer id ) {
		AjaxResult result=new AjaxResult();
		try {
			int count=certService.delete(id);
			if (count>0) {
				result.setSuccess(true);
			}else {
				result.setErrorMessage("保存失败");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			
		}
		return result;	
	}
	@ResponseBody
	@RequestMapping("/deletes")
	public Object deletes(Datas  datas ) {
		AjaxResult result=new AjaxResult();
		try {
			int count=certService.deletes(datas);
			if (count==datas.getDatas().size()) {
				result.setSuccess(true);
			}else {
				result.setErrorMessage("保存失败");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			
		}
		return result;	
	}
}	

