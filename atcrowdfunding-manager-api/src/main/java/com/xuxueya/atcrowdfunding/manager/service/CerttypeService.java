package com.xuxueya.atcrowdfunding.manager.service;

import java.util.List;

import com.xuxueya.atcrowdfunding.bean.AccountCertType;
import com.xuxueya.atcrowdfunding.bean.Cert;

public interface CerttypeService {

	List<Cert> queryAllCertType();

	int saveCertType(AccountCertType acctcertType);

	int deleteCerttype(AccountCertType acctcertType);

	List<AccountCertType> queryCertAccttype();

}
