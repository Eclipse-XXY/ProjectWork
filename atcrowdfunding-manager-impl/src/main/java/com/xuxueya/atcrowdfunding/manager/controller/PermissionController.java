package com.xuxueya.atcrowdfunding.manager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuxueya.atcrowdfunding.bean.Permission;
import com.xuxueya.atcrowdfunding.manager.service.PermissionService;
import com.xuxueya.atcrowdfunding.util.AjaxResult;

@Controller
@RequestMapping("/permission")
public class PermissionController {
	@Autowired
private PermissionService permissionService;
	@RequestMapping("/index")
	public String toIndex() {
		return "permission/index";
	}
	@RequestMapping("/add")
	public String toAdd() {
		return "permission/add";
	}
	@RequestMapping("/edit")
	public String toEdit(Integer id,Map< String, Object> map) {
	Permission permission=permissionService.getPermissionById(id);
	map.put("permission", permission);
		return "permission/edit";
	}
	/*demo1假数据的封装
	@ResponseBody
	@RequestMapping("/loadData")
	public Object doloadData() {
		AjaxResult result=new AjaxResult();
		try {
			Permission permission = new Permission("系统权限菜单");
			Permission permission1 = new Permission("控制面板");
			Permission permission2 = new Permission("消息管理");
			Permission permission3 = new Permission("权限管理");
			permission.getChildren().add(permission1);
			permission.getChildren().add(permission2);
			permission.getChildren().add(permission3);
			permission.setOpen(true);
			result.setData(permission);
			 result.setSuccess(true);
		} catch (Exception e) {
    
         result.setSuccess(false);
         e.printStackTrace();
		}
		return result;
	}
	//demo2使用数据库访问的形式展开两级结构
	@ResponseBody
	@RequestMapping("/loadData")
	public Object doloadData() {
		AjaxResult result=new AjaxResult();
		try {
			//查询树的根元素
			Permission permission =permissionService.queryRootPermission();
			//查询树的一级子元素
			List<Permission> firstChildren=permissionService.queryChildrenPermission(permission.getId());
			permission.setChildren(firstChildren);
			//查询树的二级子元素
			for (Permission firstchildrenPermission : firstChildren) {
		List<Permission> nodechildren=permissionService.queryChildrenPermission(firstchildrenPermission.getId());
		firstchildrenPermission.setChildren(nodechildren);
			}
			permission.setOpen(true);
			result.setData(permission);
			result.setSuccess(true);
		} catch (Exception e) {
			
			result.setSuccess(false);
			e.printStackTrace();
		}
		return result;
	}
	//使用递归的形式展开数据
	@ResponseBody
	@RequestMapping("/loadData")
	public Object doloadData() {
		AjaxResult result=new AjaxResult();
		try {
			//查询树的根元素
			Permission permission =permissionService.queryRootPermission();
			queryChildrenNode(permission);
		
			
			permission.setOpen(true);
			result.setData(permission);
			result.setSuccess(true);
		} catch (Exception e) {
			
			result.setSuccess(false);
			e.printStackTrace();
		}
		return result;
	}*/
	//使用一次性查询完数据库的所有数据然后在进行进行数据的组装  但是循环的次数比较多
/*	@ResponseBody
	@RequestMapping("/loadData")
	public Object doloadData() {
		AjaxResult result=new AjaxResult();
		try {
			
			 //整体的设计思路是把儿子都封装在List<chrldren>集合当中所以最后返回父亲节点就OK了
	
	List<Permission> allPermissionList =permissionService.queryAllPermission();
       Permission root=null;
 //      a:作为外层循环的跳出标志
for (Permission permission : allPermissionList) {
	if (permission.getPid()==null) {
		root=permission;
		root.setOpen(true);
	}else {
		//保存孩子节点
		Permission childrenPermission=permission;
		//因为数据都在allPermissionList集合当中所以不管是子节点还是父节点都需要遍历allPermissionList集合
		for (Permission innerpermission : allPermissionList) {
			//说明他们两具有父子关系 一般在一对多的关系当中都是使用多的一方找一的一方进行封装
			if (innerpermission.getId()==childrenPermission.getPid()) {
				innerpermission.getChildren().add(childrenPermission);
//				break a;直接跳出到a的位子也就是外层循环的跳出方式
				break ;//只跳出内层循环
			}
		}
		//第二层for循环是进行子节点的组装
		childrenPermission.setOpen(true);
	}
}
		    result.setData(root);
			result.setSuccess(true);
		} catch (Exception e) {
			
			result.setSuccess(false);
			e.printStackTrace();
		}
		return result;
	}*/
	//将循环次数从400次降低到40次
	@ResponseBody
	@RequestMapping("/loadData")
	public Object doloadData() {
		AjaxResult result=new AjaxResult();
		try {
			List<Permission> allPermissionList =permissionService.queryAllPermission();
		Map<Integer,Permission> map=new HashedMap();
		for (Permission permission : allPermissionList) {
			//将每一个permission都加上一个id便于后期的查找使用  可以是父Id找对象也可以是本身的Id找对象
			map.put(permission.getId(), permission);
		}
			
			Permission root=null;
			//      a:作为外层循环的跳出标志
			for (Permission permission : allPermissionList) {
				if (permission.getPid()==null) {
					root=permission;
					root.setOpen(true);
				}else {

					Permission children=permission;
//				通过孩子节点的pid 查找到父亲节点 找到父亲节点以后再把孩子节点追加到父亲节点的孩子集合当中
                       Permission permission2 = map.get(children.getPid());
						permission2.getChildren().add(children);
						permission2.setOpen(true);
				}
			}
			result.setData(root);
			result.setSuccess(true);
		} catch (Exception e) {
			
			result.setSuccess(false);
			e.printStackTrace();
		}
		return result;
	}
//	实现两颗许可树共用一个controller的方法
	@ResponseBody
	@RequestMapping("/asyncLoadData")
	public Object asyncLoadData(Integer roleid) {
		
		List<Permission> permissions=new ArrayList<>();
		try {
			//查询到一个角色所拥有的全部许可的id
	List<Integer> permissionIdList=permissionService.queryRolePermissionByRoleId(roleid);
			List<Permission> allPermissionList =permissionService.queryAllPermission();
		
			Map<Integer,Permission> map=new HashedMap();
			
			for (Permission permission : allPermissionList) {
				//如果许可的id在用户查询的全部许可id当中说明已经被用户选定需要设置它的选中状态
				if (permissionIdList.contains(permission.getId())) {
				permission.setChecked(true);
				}
				map.put(permission.getId(), permission);
			}
			
			Permission root=null;
			//      a:作为外层循环的跳出标志
			for (Permission permission : allPermissionList) {
				if (permission.getPid()==null) {
					root=permission;
					root.setOpen(true);
				}else {
					
					Permission children=permission;
//				通过孩子节点的pid 查找到父亲节点 找到父亲节点以后再把孩子节点追加到父亲节点的孩子集合当中
					Permission permission2 = map.get(children.getPid());
					permission2.getChildren().add(children);
					permission2.setOpen(true);
				}
			}

		permissions.add(root);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return permissions;
	}
//只可以做增删改的许可树 不可以做数据的回显工作
/*	@ResponseBody
	@RequestMapping("/asyncLoadData")
	public Object asyncLoadData() {
		//因为使用z_tree进行页面的展示它自身提供的查找数据的方法是异步的进行数据的解析
		//但是后端返回的结果必须是list集合的形式[]所以讲排好序的根节点放入集合当中就好啦
		List<Permission> permissions=new ArrayList<>();
		try {
			//需要注意的事情这个返回数据库当中的所有树的节点集合下面的遍历和for循环的操作就是对集合当中的元素进行
			//排列的意思就是给他们之间引用的指向已经发生了改变就是相当于已经排列好的一个树
			List<Permission> allPermissionList =permissionService.queryAllPermission();
			
			Map<Integer,Permission> map=new HashedMap();
			
			for (Permission permission : allPermissionList) {
				//将每一个permission都加上一个id便于后期的查找使用  可以是父Id找对象也可以是本身的Id找对象
				map.put(permission.getId(), permission);
			}
			
			Permission root=null;
			//      a:作为外层循环的跳出标志
			for (Permission permission : allPermissionList) {
				if (permission.getPid()==null) {
					root=permission;
					root.setOpen(true);
				}else {
					
					Permission children=permission;
//				通过孩子节点的pid 查找到父亲节点 找到父亲节点以后再把孩子节点追加到父亲节点的孩子集合当中
					Permission permission2 = map.get(children.getPid());
					permission2.getChildren().add(children);
					permission2.setOpen(true);
				}
			}
			
			permissions.add(root);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return permissions;
	}
	
	@ResponseBody
	@RequestMapping("/doAdd")
	public Object doAdd(Permission permission) {
		AjaxResult result=new AjaxResult();
		try {
		int count=	permissionService.savePermission(permission);
			result.setSuccess(count==1);
		} catch (Exception e) {
			
			result.setSuccess(false);
			e.printStackTrace();
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/doDelete")
	public Object doDelete(Integer id) {
		AjaxResult result=new AjaxResult();
		try {
			int count=	permissionService.deletePermission(id);
			result.setSuccess(count==1);
		} catch (Exception e) {
			
			result.setSuccess(false);
			e.printStackTrace();
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/doEdit")
	public Object doEdit(Permission permission) {
		AjaxResult result=new AjaxResult();
		try {
			int count=	permissionService.updatePermission(permission);
			result.setSuccess(count==1);
		} catch (Exception e) {
			
			result.setSuccess(false);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 递归方法的三个要点
	 * 1.是自己调用自己
	 * 2.要有结束的条件  本次循环的递归条件是没有没有父亲id就不要展开了
	 * 3.每一次的递归器范围都要缩小将近一半左右
	 * @param
	 * @author 徐雪娅
	 */
	private void queryChildrenNode(Permission permission) {
		List<Permission> innerChildren=permissionService.queryChildrenPermission(permission.getId());
		permission.setChildren(innerChildren);
		for (Permission innerpermission : innerChildren) {
			List<Permission> nodechildren=permissionService.queryChildrenPermission(innerpermission.getId());
			innerpermission.setChildren(nodechildren);
			innerpermission.setOpen(true);
			queryChildrenNode(innerpermission);
		}		
	
	}

}
