package com.xuxueya.atcrowdfunding.potal.service;

import java.util.List;
import java.util.Map;

import com.xuxueya.atcrowdfunding.bean.Member;
import com.xuxueya.atcrowdfunding.bean.MemberCert;

public interface MemberService {
	Member queryMemberForLogin(Map paramMap);

	int updateAcctType(Member member);

	int doupdatebasicinfo(Member loginmember);

	int uploadimg(List<MemberCert> memberCert);

	void updateEmail(String email);

	void upateAuthStatus(Member loginmember);

	Member queryMemberByid(Integer memberid);
}
