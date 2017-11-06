package com.xuxueya.atcrowdfunding.manager.dao;

import java.util.List;
import java.util.Map;

import com.xuxueya.atcrowdfunding.bean.ProjectType;
import com.xuxueya.atcrowdfunding.util.Datas;

public interface ProjectTypeDao {

	List<ProjectType> queryPageList(Map<String, Object> paramMap);

	int saveProjectType(ProjectType projectType);

	int queryTotalSize(Map<String, Object> paramMap);

	ProjectType queryProjectTypeById(Integer id);

	int updateProjectType(Map<String, Object> paramMap);

	int deleteProjectType(Integer id);

	int deleteAllProjectType(Datas datas);

//	int deleteAllProjectType(List ids);

}
