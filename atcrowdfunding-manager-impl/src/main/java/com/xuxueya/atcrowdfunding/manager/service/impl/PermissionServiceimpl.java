package com.xuxueya.atcrowdfunding.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuxueya.atcrowdfunding.bean.Permission;
import com.xuxueya.atcrowdfunding.manager.dao.PermissionDao;
import com.xuxueya.atcrowdfunding.manager.service.PermissionService;
@Service
public class PermissionServiceimpl implements PermissionService {
	@Autowired
 private PermissionDao permissionDao;

	@Override
	public Permission queryRootPermission() {
		return permissionDao.queryRootPermission();
	}

	@Override
	public List<Permission> queryChildrenPermission(Integer pid) {
		return permissionDao.queryChildrenPermission(pid);
	}

	@Override
	public List<Permission> queryAllPermission() {
		return permissionDao.queryAllPermission();
	}

	@Override
	public int savePermission(Permission permission) {
		return permissionDao.savePermission(permission) ;
	}

	@Override
	public Permission getPermissionById(Integer id) {
		return permissionDao.getPermissionById(id);
	}

	@Override
	public int updatePermission(Permission permission) {
		return permissionDao.updatePermission(permission);
	}

	@Override
	public int deletePermission(Integer id) {
		return permissionDao.deletePermission(id);
	}

	@Override
	public List<Integer> queryRolePermissionByRoleId(Integer roleid) {
		return  permissionDao.queryRolePermissionByRoleId(roleid);
	}



 
}
