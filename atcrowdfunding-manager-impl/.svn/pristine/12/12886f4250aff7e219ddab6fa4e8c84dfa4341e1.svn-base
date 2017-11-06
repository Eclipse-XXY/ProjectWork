package com.xuxueya.atcrowdfunding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuxueya.atcrowdfunding.bean.ProjectType;
import com.xuxueya.atcrowdfunding.manager.dao.ProjectTypeDao;
import com.xuxueya.atcrowdfunding.manager.service.ProjectTypeService;
import com.xuxueya.atcrowdfunding.util.Datas;
import com.xuxueya.atcrowdfunding.util.Page;
@Service
public class ProjectTypeServiceImpl implements ProjectTypeService {
@Autowired
	private ProjectTypeDao projectTypeDao;
/**
 * 记住一点泛型就是表示你在其他的地方都可以用传过来是什么类型就是什么类型在子类当中使用的时候不需要在做特殊的
 * 数据类型的传递
 * @param
 * @author 徐雪娅
 */
@Override
public Page queryPage(Map<String, Object> paramMap) {
	int pageNo=(Integer)paramMap.get("pageNo");
	int pageSize=(Integer)paramMap.get("pageSize");
	Page<Object> page = new Page<>(pageNo, pageSize);
	paramMap.put("startIndex", page.getStartIndex());
   List data=projectTypeDao.queryPageList(paramMap);
   int totalSize=projectTypeDao.queryTotalSize(paramMap);
   //这个地方不仅需要返回遍历的集合也要返回集合的数据便于做分页使用
   page.setTotalSize(totalSize);
   page.setData(data);
	return page;
}

@Override
public ProjectType queryProjectTypeById(Integer id) {
	return projectTypeDao.queryProjectTypeById(id);
}
@Override
public int updateProjectType(Map<String, Object> paramMap) {
	return  projectTypeDao.updateProjectType(paramMap);
}
@Override
public int saveProjectType(ProjectType projectType) {
	return projectTypeDao.saveProjectType(projectType);
}

@Override
public int deleteProjectType(Integer id) {
	return projectTypeDao.deleteProjectType(id);
}

@Override
public int deleteAllProjectType(Datas datas) {
	return projectTypeDao.deleteAllProjectType(datas);
}

//@Override
//public int deleteAllProjectType(List ids) {
//	return projectTypeDao.deleteAllProjectType(ids);
//} 
}
