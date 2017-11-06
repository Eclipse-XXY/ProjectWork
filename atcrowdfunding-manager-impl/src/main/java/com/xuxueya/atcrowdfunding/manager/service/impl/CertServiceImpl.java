package com.xuxueya.atcrowdfunding.manager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;








import com.xuxueya.atcrowdfunding.bean.Cert;
import com.xuxueya.atcrowdfunding.manager.dao.CertDao;
import com.xuxueya.atcrowdfunding.manager.service.CertService;
import com.xuxueya.atcrowdfunding.util.Datas;
import com.xuxueya.atcrowdfunding.util.Page;

@Service
public class CertServiceImpl implements CertService {
@Autowired
	private CertDao certDao;

@Override
public Page<Cert> queryPage(Map<String, Object> paramMap) {
	Page<Cert> page=new Page<Cert>((int)paramMap.get("pageNo"), (int)paramMap.get("pageSize"));
	paramMap.put("startIndex",page.getStartIndex());
	List<Cert> data=certDao.queryPage(paramMap);
	
	 int totalSize=certDao.queryTotalSize(paramMap);
	 page.setData(data);
	 page.setTotalSize(totalSize);
	return page ;
}

@Override
public int saveCert(Cert cert) {
	Map<String, Object> paramMap=new HashMap<String, Object>();
	paramMap.put("cert", cert);
	return certDao.saveCert(paramMap) ;
}

@Override
public Cert queryCert(int id) {
	return certDao.queryCert(id);
}

@Override
public int update(Map<String, Object> paramMap) {
	return certDao.update(paramMap);
}

@Override
public int delete(Integer id) {
	return certDao.delete(id);
}

@Override
public int deletes(Datas datas) {
	return certDao.deletes(datas);
}

@Override
public List<Cert> queryAcctCert(String accttype) {
	// TODO Auto-generated method stub
	return certDao.queryAcctCert(accttype);
}

}
