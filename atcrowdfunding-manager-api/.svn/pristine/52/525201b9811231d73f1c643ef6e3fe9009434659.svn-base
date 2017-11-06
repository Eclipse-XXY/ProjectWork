package com.xuxueya.atcrowdfunding.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xuxueya.atcrowdfunding.bean.Role;
import com.xuxueya.atcrowdfunding.util.Datas;
import com.xuxueya.atcrowdfunding.util.Page;


public interface RoleDao {

	List<Role> queryPage(Map<String, Object> paramMap);

	int queryAllSize(Map<String, Object> paramMap);

	int saveRole(Role role);

	Role queryRoleByTd(int id);

	int updateRole(Role role);

	int deleteRole(Integer id);

	int batchDelete(Datas datas);

	List<Role> queryAll();

	List<Integer> queryRoleIdByUserId(Integer id);

	void delePreAssignRolePermissionShip(Integer roleid);

	int AssignRolePermissionShip( @Param("permissionids")List<Integer> permissionids,@Param("roleid") Integer roleid);

}
