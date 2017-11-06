package com.xuxueya.atcrowdfunding.manager.service;

import java.util.List;
import java.util.Map;

import com.xuxueya.atcrowdfunding.bean.Advert;
import com.xuxueya.atcrowdfunding.bean.User;
import com.xuxueya.atcrowdfunding.util.Page;

public interface AdvertService {

	Page<Advert> queryPage(Map<String, Object> paramMap);

	int saveAdvert(Advert advert);

	Advert queryAdvertById(Integer id);

	int updateAdvert(Advert advert);

	int deleteAdvert(Integer id);

	int batchDelete(List<User> datas);

	



}
