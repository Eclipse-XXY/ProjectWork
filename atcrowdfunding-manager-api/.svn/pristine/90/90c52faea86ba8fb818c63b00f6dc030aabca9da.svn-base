package com.xuxueya.atcrowdfunding.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xuxueya.atcrowdfunding.bean.Advert;
import com.xuxueya.atcrowdfunding.bean.User;

public interface AdvertDao {

	Integer queryAllSize(Map<String, Object> paramMap);

//  List<Advert> qureyPage( Map<String, Object> paramMap);

List<Advert> qureyPage(Map<String, Object> paramMa);

int saveAdvert(Advert advert);

Advert queryAdvertById(Integer id);

int updateAdvert(Advert advert);

int deleteAdvert(Integer id);

int batchDelete(List<User> advertlist);





}
