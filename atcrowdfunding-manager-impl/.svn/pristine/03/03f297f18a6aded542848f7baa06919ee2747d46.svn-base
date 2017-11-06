package com.xuxueya.atcrowdfunding.manager.controller;

import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.RequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuxueya.atcrowdfunding.bean.Param;
import com.xuxueya.atcrowdfunding.manager.service.ParamService;
import com.xuxueya.atcrowdfunding.util.AjaxResult;
import com.xuxueya.atcrowdfunding.util.Datas;
import com.xuxueya.atcrowdfunding.util.Page;
import com.xuxueya.atcrowdfunding.util.StringUtil;
@RequestMapping("/param")
@Controller
public class ParamController {
	@Autowired
private ParamService  paramService; 
	@RequestMapping("/index")
	public String toIndex() {
		return "param/index";
	}
	@RequestMapping("/add")
	public String toadd() {
		return "param/add";
	}
	@RequestMapping("/edit")
	public String toedit(Integer id,Map<String, Object> map) {
		Param param=paramService.queryParamByID(id);
		if (param==null) {
			System.out.println(param+"chanshu  nhfhskjddddddddd产生女孩");
		}
		map.put("paramObj", param);
		//把数据封装在map当中传递数据，也可以使用map来接受数据是根据key的值进行接收数据，或是使用对象
		return "param/edit";
	}
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object dopageQuery(@RequestParam(value="pageNo",defaultValue="1",required=false)
	Integer pageNo,@RequestParam(value="pageSize",defaultValue="3",required=false)
	Integer pageSize,String pagetext) {
		AjaxResult result=new AjaxResult();
		Map<String , Object> paraMap=new HashMap<String, Object> ();
		paraMap.put("pageNo", pageNo);
		paraMap.put("pageSize", pageSize);
		if (StringUtil.isNotEmpty(pagetext)) {
			pagetext=pagetext.replaceAll("%", "\\\\%");
			paraMap.put("pagetext", pagetext);
		}
		try {
			Page<Param> page=paramService.queryPage(paraMap);
				result.setPage(page);
				result.setSuccess(true);
		} catch (Exception e) {
			result.setErrorMessage("查询失败");
			result.setSuccess(false);
			e.printStackTrace();
		}
		
		return result;
	}
	@ResponseBody
	@RequestMapping("/insert")
	public Object doinsert(Param param) {
		AjaxResult result=new AjaxResult();
		try {
			int count=paramService.insert(param);
			if(count>0) {
			result.setSuccess(true);
			}
		} catch (Exception e) {
			result.setErrorMessage("保存失败");
			result.setSuccess(false);
			e.printStackTrace();
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Object doupdate(Param param) {
		AjaxResult result=new AjaxResult();
		try {
			int count=paramService.update(param);
			if(count>0) {
				result.setSuccess(true);
			}
		} catch (Exception e) {
			result.setErrorMessage("编辑失败");
			result.setSuccess(false);
			e.printStackTrace();
		}
		
		return result;
	}
	@ResponseBody
	@RequestMapping("/delete")
	public Object dodelete(Integer id) {
		AjaxResult result=new AjaxResult();
		try {
			int count=paramService.delete(id);
			if(count>0) {
				result.setSuccess(true);
			}
		} catch (Exception e) {
			result.setErrorMessage("编辑失败");
			result.setSuccess(false);
			e.printStackTrace();
		}
		
		return result;
	}
	@ResponseBody
	@RequestMapping("/deletes")
	public Object dodeletes(Datas datas) {
		AjaxResult result=new AjaxResult();
		try {
			int count=paramService.deletes(datas);
			if(count==datas.getDatas().size()) {
				result.setSuccess(true);
			}
		} catch (Exception e) {
			result.setErrorMessage("编辑失败");
			result.setSuccess(false);
			e.printStackTrace();
		}
		
		return result;
	}
	

}
