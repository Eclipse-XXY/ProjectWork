package com.xuxueya.atcrowdfunding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuxueya.atcrowdfunding.bean.Advert;
import com.xuxueya.atcrowdfunding.bean.User;
import com.xuxueya.atcrowdfunding.manager.dao.AdvertDao;
import com.xuxueya.atcrowdfunding.manager.service.AdvertService;
import com.xuxueya.atcrowdfunding.util.Page;
@Service
public class AdvertServiceimpl implements AdvertService {
	@Autowired
private AdvertDao adverDao;

	@Override
	public Page<Advert> queryPage(Map<String, Object> paramMap) {
		Integer pageNo=(Integer) paramMap.get("pageNo");
		Integer pageSize=(Integer) paramMap.get("pageSize");
		Page<Advert> page = new Page<>(pageNo, pageSize);
		int startIndex = page.getStartIndex();
        paramMap.put("startIndex", startIndex);
		Integer totalSize=adverDao.queryAllSize(paramMap);
		
		List<Advert> datas=adverDao.qureyPage(paramMap);

		System.out.println(datas);
		page.setTotalSize(totalSize);
		page.setData(datas);
		return page;
	}

	@Override
	public int saveAdvert(Advert advert) {
		return adverDao.saveAdvert( advert); 
	}

	@Override
	public Advert queryAdvertById(Integer id) {
		return adverDao.queryAdvertById(id);
	}

	@Override
	public int updateAdvert(Advert advert) {
		return adverDao.updateAdvert(advert);
	}

	@Override
	public int deleteAdvert(Integer id) {
		return adverDao.deleteAdvert(id);
	}

	@Override
	public int batchDelete(List<User> datas) {
		return  adverDao.batchDelete(datas);
	}

}
