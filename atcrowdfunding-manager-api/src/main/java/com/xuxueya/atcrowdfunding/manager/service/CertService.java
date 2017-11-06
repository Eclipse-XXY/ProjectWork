package com.xuxueya.atcrowdfunding.manager.service;

import java.util.List;
import java.util.Map;

import com.xuxueya.atcrowdfunding.bean.Cert;
import com.xuxueya.atcrowdfunding.util.Datas;
import com.xuxueya.atcrowdfunding.util.Page;

public interface CertService {

	Page<Cert> queryPage(Map<String, Object> paramMap);

	int saveCert(Cert cert);

	Cert queryCert(int id);

	int update(Map<String, Object> paramMap);

	int delete(Integer id);

	int deletes(Datas datas);

	List<Cert> queryAcctCert(String accttype);

}
