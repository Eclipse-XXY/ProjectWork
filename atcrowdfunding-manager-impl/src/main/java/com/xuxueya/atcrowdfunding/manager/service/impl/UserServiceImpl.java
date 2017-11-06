package com.xuxueya.atcrowdfunding.manager.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuxueya.atcrowdfunding.bean.Permission;
import com.xuxueya.atcrowdfunding.bean.User;
import com.xuxueya.atcrowdfunding.bean.UserRole;
import com.xuxueya.atcrowdfunding.manager.dao.UserDao;
import com.xuxueya.atcrowdfunding.manager.service.UserService;
import com.xuxueya.atcrowdfunding.util.Const;
import com.xuxueya.atcrowdfunding.util.Datas;
import com.xuxueya.atcrowdfunding.util.Date_StringUtil;
import com.xuxueya.atcrowdfunding.util.Page;
@Service
public class UserServiceImpl implements UserService {
 @Autowired
	private  UserDao userDao;
	@Override
	public User queryUserForLogin(Map<String, Object> map) {
		return userDao.queryUserForLogin(map);
	}

/**应该在业务逻辑层写具体的逻辑代码的实现
 * 
 * @param
 * @author 徐雪娅
 */
	@Override
	public Page<User> queryPage(Map<String, Object> paramMap) {
		Page<User> page = new Page<User>((int)paramMap.get("pageno"),(int)paramMap.get("pagesize"));
		paramMap.put("startIndex", page.getStartIndex());

		//查询到每页应该显示的数据集合然后赋值给page的date集合做封装
		List<User> list =userDao.queryPage(paramMap);
		page.setData(list);
		page.setTotalSize(userDao.queryAllsize(paramMap));
		return page;
	}

@Override
public int addUser(User user) {
user.setUserpswd(Const.PASSWORD);
user.setCreatetime(Date_StringUtil.dateToString(new Date()));
return userDao.addUser(user);
}

@Override
public User getUserById(Integer id) {
	return userDao.getUserById(id);
}

@Override
public int updateUser( User user) {
	return userDao.updateUser( user);
}

@Override
public int deleteUser(Integer id) {
	return userDao.deleteUser(id);
}





@Override
public int deleteUserBatch(List<User> datas) {
	
return userDao.deleteUserBatch(datas);
}

@Override
public List<Integer> queryRoleIdByUserId(Integer id) {
	return userDao.queryRoleIdByUserId(id);
}

@Override
public int saveUserRole(Integer userid, List<Integer> ids) {
	int count=0;
for (Integer roleid : ids) {
	UserRole userRole = new UserRole(userid, roleid);
	count+=userDao.saveUserRoleRelationship(userRole);
}
	return count;
}

@Override
public int deleteUserRole(Integer userid, List<Integer> ids) {
	
	return userDao.deleteUserRole(userid, ids);
}

@Override
public List<Permission> queryUserPermissionByUserId(Integer id) {
	return userDao.queryUserPermissionByUserId(id);
}}
