package com.xuxueya.atcrowdfunding.manager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuxueya.atcrowdfunding.bean.Role;
import com.xuxueya.atcrowdfunding.manager.service.RoleService;
import com.xuxueya.atcrowdfunding.util.AjaxResult;
import com.xuxueya.atcrowdfunding.util.Datas;
import com.xuxueya.atcrowdfunding.util.Page;
import com.xuxueya.atcrowdfunding.util.StringUtil;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
private RoleService roleService;

	@RequestMapping("/index")
	private String  torolepPge() {
		return"role/index";
	}
	@RequestMapping("/add")
	private String  toadd() {
		return"role/add";
	}
	
	@RequestMapping("/edit")
	private String  toedit(int id,Map<String, Object> map) {
		Role role=roleService.queryRoleByTd(id);
		map.put("role", role);
		return"role/edit";
	}
	
	@RequestMapping("/assign")
	private String  toassign(int roleId,Map<String, Object> map) {
		Role role=roleService.queryRoleByTd(roleId);
		map.put("role", role);
		return"role/assign";
	}
	
	@ResponseBody
	@RequestMapping("/doindex")
	private Object doindex(@RequestParam(value="pageNo",required=false,defaultValue="1")Integer pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="3")Integer pageSize,String queryText){
		AjaxResult result=new AjaxResult();
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("pageNo", pageNo);
		paramMap.put("pageSize", pageSize);
		if (StringUtil.isNotEmpty(queryText)) {
			queryText=queryText.replaceAll("%", "\\\\%");
			paramMap.put("queryText", queryText);
		}
		try {
		Page<Role> page=roleService.queryPage(paramMap);
		if (page.getData().size()>0) {
			result.setPage(page);
			result.setSuccess(true);
		}
		else {
			result.setErrorMessage("分页失败");
			result.setSuccess(false);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/doAdd")
	private Object doAdd(Role role){
		AjaxResult result=new AjaxResult();
		try {
			int count=roleService.saveRole(role);
			if (count>0) {
				result.setSuccess(true);
			}
			else {
				result.setErrorMessage("保存失败");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/doEdit")
	private Object doEdit(Role role){
		AjaxResult result=new AjaxResult();
		try {
			int count=roleService.updateRole(role);
			if (count>0) {
				result.setSuccess(true);
			}
			else {
				result.setErrorMessage("保存失败");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/deleteRole")
	private Object deleteRole(Integer id){
		AjaxResult result=new AjaxResult();
		try {
			int count=roleService.deleteRole(id);
			if (count>0) {
				result.setSuccess(true);
			}
			else {
				result.setErrorMessage("删除失败");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/batchDelete")
	private Object batchDelete(Datas datas){

		AjaxResult result=new AjaxResult();
		try {
			int count=roleService.batchDelete(datas);
			if (count==datas.getDatas().size()) {
				result.setSuccess(true);
			}
			else {
				result.setErrorMessage("批量删除失败");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/doAssignRolePermission")
	private Object doAssignRolePermission(Datas datas,Integer roleid){
		
		AjaxResult result=new AjaxResult();
		try {
			int count=roleService.AssignRolePermissionShip(datas.getIds(),roleid);
			if (count==datas.getIds().size()) {
				result.setSuccess(true);
			}
			else {
				result.setErrorMessage("分配成功");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
