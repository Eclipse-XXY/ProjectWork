package com.xuxueya.atcrowdfunding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuxueya.atcrowdfunding.manager.dao.MemberCertDao;
import com.xuxueya.atcrowdfunding.manager.service.MemberCertService;
@Service
public class MemberCertServiceimpl implements MemberCertService {
	@Autowired
private MemberCertDao memberCertDao;

	@Override
	public List<Map<String, Object>> queryMemberCertbyMebId(Integer memberid) {
		return memberCertDao.queryMemberCertbyMebId(memberid);
	}
}
