package com.xuxueya.atcrowdfunding.manager.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xuxueya.atcrowdfunding.bean.Permission;
import com.xuxueya.atcrowdfunding.bean.User;
import com.xuxueya.atcrowdfunding.bean.UserRole;
import com.xuxueya.atcrowdfunding.util.Datas;


public interface UserDao {
	 User queryUserForLogin(Map<String, Object> map);

	List<User> queryPage(Map<String, Object> paramMap);

	int queryAllsize(Map<String, Object> paramMap);

	int addUser(User user);

	User getUserById(Integer id);

	int updateUser(User user);

	int deleteUser(Integer id);

	int deleteUserBatch(List<User> datas);

	List<Integer> queryRoleIdByUserId(Integer id);

	int saveUserRoleRelationship(UserRole userRole);

	int deleteUserRole(@Param("userid")Integer userid, @Param("ids")List<Integer> ids);

	List<Permission> queryUserPermissionByUserId(Integer userid);
}
