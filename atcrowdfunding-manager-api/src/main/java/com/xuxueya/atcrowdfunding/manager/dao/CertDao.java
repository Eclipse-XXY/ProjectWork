package com.xuxueya.atcrowdfunding.manager.dao;

import java.util.List;
import java.util.Map;

import com.xuxueya.atcrowdfunding.bean.Cert;
import com.xuxueya.atcrowdfunding.util.Datas;

public interface CertDao {

	List<Cert> queryPage(Map<String, Object> paramMap);

	int queryTotalSize(Map<String, Object> paramMap);

	int saveCert(Map<String, Object> paramMap);

	Cert queryCert(int id);

	int update(Map<String, Object> paramMap);

	int delete(Integer id);

	int deletes(Datas datas);

	List<Cert> queryAcctCert(String accttype);

}
