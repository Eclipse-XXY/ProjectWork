package com.xuxueya.atcrowdfunding.manager.service;

import java.util.List;

import com.xuxueya.atcrowdfunding.bean.Permission;

public interface PermissionService {

	Permission queryRootPermission();

	List<Permission> queryChildrenPermission(Integer pid);

	List<Permission> queryAllPermission();

	int savePermission(Permission permission);

	Permission getPermissionById(Integer id);

	int updatePermission(Permission permission);

	int deletePermission(Integer id);

	List<Integer> queryRolePermissionByRoleId(Integer roleid);



}
