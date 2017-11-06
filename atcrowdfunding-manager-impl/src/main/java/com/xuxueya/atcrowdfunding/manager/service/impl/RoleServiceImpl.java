package com.xuxueya.atcrowdfunding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuxueya.atcrowdfunding.bean.Role;
import com.xuxueya.atcrowdfunding.manager.dao.RoleDao;
import com.xuxueya.atcrowdfunding.manager.service.RoleService;
import com.xuxueya.atcrowdfunding.util.Datas;
import com.xuxueya.atcrowdfunding.util.Page;
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
private RoleDao roleDao;
	@Override
	public Page<Role> queryPage(Map<String, Object> paramMap) {
		int pageNo=(Integer)paramMap.get("pageNo");
		int pageSize=(Integer)paramMap.get("pageSize");
		Page<Role> page=new Page<>(pageNo,pageSize);
		paramMap.put("startIndex", page.getStartIndex());
		int totalSize=roleDao.queryAllSize(paramMap);
		page.setTotalSize(totalSize);
		List<Role> roleData = roleDao.queryPage(paramMap);
		page.setData(roleData);
		return page; 
	}
	@Override
	public int saveRole(Role role) {
		return roleDao.saveRole(role);
	}
	@Override
	public Role queryRoleByTd(int id) {
		return roleDao.queryRoleByTd(id);
	}
	@Override
	public int updateRole(Role role) {
		return roleDao.updateRole(role);
	}
	@Override
	public int deleteRole(Integer id) {
		return roleDao.deleteRole(id);
	}
	@Override
	public int batchDelete(Datas datas) {
 
		return roleDao.batchDelete(datas);
	}
	@Override
	public List<Role> queryAll() {
		return roleDao.queryAll();
	}
	@Override
	public int AssignRolePermissionShip(List<Integer> premissionids, Integer roleid) {
		//删除之前的关系
		roleDao.delePreAssignRolePermissionShip(roleid);
		return roleDao.AssignRolePermissionShip(premissionids,roleid);
	}
	

}
