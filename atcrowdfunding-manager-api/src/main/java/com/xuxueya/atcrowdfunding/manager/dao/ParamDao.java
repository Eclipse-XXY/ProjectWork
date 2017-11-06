package com.xuxueya.atcrowdfunding.manager.dao;

import java.util.List;
import java.util.Map;

import com.xuxueya.atcrowdfunding.bean.Param;
import com.xuxueya.atcrowdfunding.util.Datas;

public interface ParamDao {

	int queryTotalSize(Map<String, Object> paraMap);

	List<Param> queryPage(Map<String, Object> paraMap);

	int insert(Param param);

	Param queryParamByID(Integer id);

	int update(Param param);

	int delete(Integer id);

	int deletes(Datas datas);

}
