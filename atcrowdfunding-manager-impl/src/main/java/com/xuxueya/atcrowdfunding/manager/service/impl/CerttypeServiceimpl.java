package com.xuxueya.atcrowdfunding.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuxueya.atcrowdfunding.bean.AccountCertType;
import com.xuxueya.atcrowdfunding.bean.Cert;
import com.xuxueya.atcrowdfunding.manager.dao.CerttypeDao;
import com.xuxueya.atcrowdfunding.manager.service.CerttypeService;
@Service
public class CerttypeServiceimpl implements CerttypeService {
	@Autowired
private CerttypeDao certtypeDao;

	@Override
	public List<Cert> queryAllCertType() {
		return certtypeDao.queryAllCertType();
	}

	@Override
	public int saveCertType(AccountCertType acctcertType) {
		return certtypeDao.saveCertType(acctcertType);
	}

	@Override
	public int deleteCerttype(AccountCertType acctcertType) {
		return certtypeDao.deleteCerttype(acctcertType);
	}

	@Override
	public List<AccountCertType> queryCertAccttype() {
		return certtypeDao.queryCertAccttype();
	}
}
