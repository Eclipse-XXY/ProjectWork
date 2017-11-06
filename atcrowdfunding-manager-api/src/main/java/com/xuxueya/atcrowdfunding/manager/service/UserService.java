package com.xuxueya.atcrowdfunding.manager.service;

import java.util.List;
import java.util.Map;

import com.xuxueya.atcrowdfunding.bean.Permission;
import com.xuxueya.atcrowdfunding.bean.Role;
import com.xuxueya.atcrowdfunding.bean.User;
import com.xuxueya.atcrowdfunding.util.Datas;
import com.xuxueya.atcrowdfunding.util.Page;


/**
 *该处的方法用map目的是他是接口我可以接受多个参数便于实现类或者是框架的代理类的利用
 * @param
 * @author 徐雪娅
 */
public interface UserService    {
	
 User queryUserForLogin(Map<String,Object> map);

Page<User> queryPage(Map<String, Object> paramMap);

int addUser(User user);

User getUserById(Integer id);

int updateUser(User user);

int deleteUser(Integer id);

int deleteUserBatch(List<User> list);

List<Integer> queryRoleIdByUserId(Integer id);

int saveUserRole(Integer userid, List<Integer> ids);

int deleteUserRole(Integer userid, List<Integer> ids);

List<Permission> queryUserPermissionByUserId(Integer id);
}
