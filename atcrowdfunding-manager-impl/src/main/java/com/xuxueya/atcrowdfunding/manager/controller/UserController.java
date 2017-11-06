package com.xuxueya.atcrowdfunding.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuxueya.atcrowdfunding.bean.Role;
import com.xuxueya.atcrowdfunding.bean.User;
import com.xuxueya.atcrowdfunding.manager.service.RoleService;
import com.xuxueya.atcrowdfunding.manager.service.UserService;
import com.xuxueya.atcrowdfunding.util.AjaxResult;
import com.xuxueya.atcrowdfunding.util.Datas;
import com.xuxueya.atcrowdfunding.util.Page;
import com.xuxueya.atcrowdfunding.util.StringUtil;
/**
 * 用于处理管理员的相关操作
 * @param
 * @author 徐雪娅
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
private UserService userService;
	@Autowired
	private RoleService roleService;
	
	//同步的分页请求方式
//	@RequestMapping(value="/index",method=RequestMethod.GET)
//	public String queryPage(@RequestParam(value="pageNo",required=false,defaultValue="1") int pageNo,
//			@RequestParam(value="pageSize",required=false,defaultValue="2")int pageSize,Map< String , Object > map) {
//		框架当中想给请求的参数赋值默认的值可以使用@RequestParam标签让这种初始化的事情交给框架去做
//		//该parammap是用来保存接收的参数的
//		
//		Map<String, Object> paramMap=new HashMap<String, Object>();
//		
//		paramMap.put("pagesize", pageSize);
//		paramMap.put("pageno", pageNo);
//	
//		Page<User> currpagelist = userService.queryPage(paramMap);
//		//这个map是放到请求域当中的
//		map.put(Const.PAGE, currpagelist);
//		return "user/index";
//	}
	
	
	
	//异步的分页请求方式
//	框架当中想给请求的参数赋值默认的值可以使用@RequestParam标签让这种初始化的事情交给框架去做
	//该parammap是用来保存接收的参数的
	//直接用于界面的展示
	@RequestMapping(value="/index")
	public String index() {
		return "user/index";
	}
 
	//真的的进行数据的传输
	//表示传到客户端是以JSON字符串的形式
/*	@ResponseBody
	@RequestMapping(value="/doindex")
public Object doindex(@RequestParam(value="pageNo",required=false,defaultValue="1") int pageNo,
		@RequestParam(value="pageSize",required=false,defaultValue="2")int pageSize,Map< String , Object > map) {
		AjaxResult result = new AjaxResult();
	try {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("pagesize", pageSize);
		paramMap.put("pageno", pageNo);
		Page<User> currpagelist = userService.queryPage(paramMap);
		result.setPage(currpagelist);
		result.setSuccess(true);
	} catch (Exception e) {
		result.setErrorMessage("查询信息失败");
		result.setSuccess(false);
	}

	return result;
}*/
	@ResponseBody
	@RequestMapping(value="/doindex")
	public Object doindex(@RequestParam(value="pageNo",required=false,defaultValue="1") int pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="2")int pageSize,String queryText,Map< String , Object > map) {
		AjaxResult result = new AjaxResult();
		try {
			Map<String, Object> paramMap=new HashMap<String, Object>();
			paramMap.put("pagesize", pageSize);
			paramMap.put("pageno", pageNo);
			if (StringUtil.isNotEmpty(queryText) && queryText.contains("%")) {
				queryText=queryText.replaceAll("%", "\\\\%");
			}
			paramMap.put("queryText", queryText);
			
			Page<User> currpagelist = userService.queryPage(paramMap);
			result.setPage(currpagelist);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setErrorMessage("查询信息失败");
			result.setSuccess(false);
		}
		
		return result;
	}
	@RequestMapping(value="/toAdd")
	public String toAdd() {
		return "user/add";
	}
	@RequestMapping(value="/assignrole")
	public String toAssignrole(Integer id,Map<String , Object> map) {
		//第一步查询用户
		User user = userService.getUserById(id);
		map.put("user", user);
		//查询角色列表做遍历
		List<Role> rolelist =roleService.queryAll();
		//将用户没有分配的角色放一个集合当中
		List<Role> unassignrole=new ArrayList<>();
//		将用户已经分配的角色放到一个集合当中
		List<Role> assignrole=new ArrayList<>();
//		根据用户ID查询用户已经分配的角色的ID
		List<Integer> roleids=userService.queryRoleIdByUserId(id);
//		进行遍历将数据放入刚才的两个集合当中
		for (Role role : rolelist) {
			if (roleids.contains(role.getId())) {
				assignrole.add(role);
			}else {
				unassignrole.add(role);
			}
		}
		
		map.put("assignrole", assignrole);
		map.put("unassignrole", unassignrole);
		
		return "user/assignRole";
	}
	@RequestMapping(value="/edit")
	public String edit(Integer id,Map<String,Object> map) {
		User user=userService.getUserById(id);
		map.put("user", user);
		return "user/edit";
	}
	@ResponseBody
	@RequestMapping(value="/doAdd")
	public Object doAdd(User user) {
		AjaxResult result = new AjaxResult();
		try {
			int count=userService.addUser(user);
				result.setSuccess(count==1);
				return result;
			} catch (Exception e) {
		    e.getStackTrace();
		    result.setErrorMessage("保存失败!");
		    result.setSuccess(false);
			return result;
			}
		}
	@ResponseBody
	@RequestMapping(value="/doEdit")
	public Object doEdit(User user) {
		AjaxResult result = new AjaxResult();
		try {
			int count=userService.updateUser(user);
				result.setSuccess(count==1);
				return result;
			} catch (Exception e) {
		    e.getStackTrace();
		    result.setSuccess(false);
			return result;
		}
	}
	@ResponseBody
	@RequestMapping(value="/doDelete")
	public Object doDelete(Integer id) {
		AjaxResult result = new AjaxResult();
		try {
			int count=userService.deleteUser(id);
			result.setSuccess(count==1);
			return result;
		} catch (Exception e) {
			e.getStackTrace();
			result.setSuccess(false);
			return result;
		}
	}
	/**
	 * 第一个Datas参数是表示他是一个数据类而datas.getDatas()表示的是一个list集合
	 * 且集合里面的泛型是User类
	 * @param
	 * @author 徐雪娅
	 */
	@ResponseBody
	@RequestMapping(value="/doDeleteBatch")
	public Object doDeleteBatch(Datas datas) {
		AjaxResult result = new AjaxResult();
		try {
			int count=userService.deleteUserBatch(datas.getDatas());
			result.setSuccess(count==datas.getDatas().size());
			return result;
		} catch (Exception e) {
			e.getStackTrace();
			result.setSuccess(false);
			return result;
		}
	}
	@ResponseBody
	@RequestMapping(value="/assignRole")
	public Object doassignRole(Integer userid,Datas datas) {
		AjaxResult result = new AjaxResult();
	
		try {
			int count =userService.saveUserRole(userid,datas.getIds());
			result.setSuccess(count==datas.getIds().size());
			
		} catch (Exception e) {
			e.getStackTrace();
			result.setSuccess(false);
			
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(value="/unassignRole")
	public Object dounassignRole(Integer userid,Datas datas) {
		AjaxResult result = new AjaxResult();
		
		try {
			int count =userService.deleteUserRole(userid,datas.getIds());
			result.setSuccess(count==datas.getIds().size());
			
		} catch (Exception e) {
			e.getStackTrace();
			result.setSuccess(false);
			
		}
		return result;
	}
	
	}
