package com.xuxueya.atcrowdfunding.manager.service;

import java.util.List;
import java.util.Map;

import com.xuxueya.atcrowdfunding.bean.Role;
import com.xuxueya.atcrowdfunding.util.Datas;
import com.xuxueya.atcrowdfunding.util.Page;

public interface RoleService  {

	Page<Role> queryPage(Map<String, Object> paramMap);

	int saveRole(Role role);

	Role queryRoleByTd(int id);

	int updateRole(Role role);

	int deleteRole(Integer id);

	int batchDelete(Datas datas);

	List<Role> queryAll();

	int AssignRolePermissionShip(List<Integer> list, Integer roleid);



}
