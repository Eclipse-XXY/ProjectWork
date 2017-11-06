package com.xuxueya.atcrowdfunding.manager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuxueya.atcrowdfunding.bean.ProjectType;
import com.xuxueya.atcrowdfunding.manager.service.ProjectTypeService;
import com.xuxueya.atcrowdfunding.util.AjaxResult;
import com.xuxueya.atcrowdfunding.util.Datas;
import com.xuxueya.atcrowdfunding.util.Page;
import com.xuxueya.atcrowdfunding.util.StringUtil;

@RequestMapping("projectType")
@Controller
public class ProjectTypeController {
	@Autowired
	private ProjectTypeService projectTypeService;

	@RequestMapping("index")
	public String toIndex() {
		return "type/index";
	}
	@RequestMapping("add")
	public String toAdd() {
		return "type/add";
	}
	
	@RequestMapping("edit")
	public String toEdit(Integer id,Map<String, Object> map) {
		
		ProjectType projectType=projectTypeService.queryProjectTypeById(id);
		map.put("projectType", projectType);
		return "type/edit";
	}

	@ResponseBody
	@RequestMapping("pageQuery")
	public Object pageQuery(
			@RequestParam(value = "pageNo", defaultValue = "1", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "1", required = false) Integer pageSize,
		   String pagetext) {
		AjaxResult result = new AjaxResult();

		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("pageNo", pageNo);
			paramMap.put("pageSize", pageSize);
			if (StringUtil.isNotEmpty(pagetext)) {
				paramMap.put("pagetext", pagetext);
			}
			Page<ProjectType> page=projectTypeService.queryPage(paramMap);
			result.setPage(page);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setErrorMessage("没有项目分类的信息");
			result.setSuccess(false);
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("saveProjectType")
	public Object saveProjectType(ProjectType projectType) {
		AjaxResult result = new AjaxResult();
		try {
			int  count=projectTypeService.saveProjectType(projectType);
			if (count>0) {
				result.setSuccess(true);
			}else {
				result.setErrorMessage("没有项目分类的信息");
				result.setSuccess(false);
			}
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("updateProjectType")
	public Object updateProjectType(Integer id,ProjectType projectType) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("projectType", projectType);
		AjaxResult result = new AjaxResult();
		try {
			int  count=projectTypeService.updateProjectType(paramMap);
			if (count>0) {
				result.setSuccess(true);
			}else {
				result.setErrorMessage("修改失败");
				result.setSuccess(false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("deleteProjectType")
	public Object deleteProject(Integer id) {
		AjaxResult result = new AjaxResult();
		try {
			int  count=projectTypeService.deleteProjectType(id);
			if (count>0) {
				result.setSuccess(true);
			}else {
				result.setErrorMessage("删除失败");
				result.setSuccess(false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("deleteAllProjectType")
	public Object deleteAllProject(Datas datas) {
		AjaxResult result = new AjaxResult();
		try {
			int  count=projectTypeService.deleteAllProjectType(datas);
			if (count==datas.getDatas().size()) {
				result.setSuccess(true);
			}else {
				result.setErrorMessage("批量删除失败");
				result.setSuccess(false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
//	@ResponseBody
//	@RequestMapping("deleteAllProjectType")
//	public Object deleteAllProject(Datas datas) {
//		AjaxResult result = new AjaxResult();
//		try {
//			int  count=projectTypeService.deleteAllProjectType(datas.getIds());
//			if (count==datas.getDatas().size()) {
//				result.setSuccess(true);
//			}else {
//				result.setErrorMessage("批量删除失败");
//				result.setSuccess(false);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//	
	@ResponseBody
	@RequestMapping("updateProjectType")
	public Object updateProjectTypef(Integer id,ProjectType projectType) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("projectType", projectType);
		AjaxResult result = new AjaxResult();
		try {
			int  count=projectTypeService.updateProjectType(paramMap);
			if (count>0) {
				result.setSuccess(true);
			}else {
				result.setErrorMessage("修改失败");
				result.setSuccess(false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
