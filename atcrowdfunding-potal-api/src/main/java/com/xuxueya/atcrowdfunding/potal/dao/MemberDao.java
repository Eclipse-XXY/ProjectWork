package com.xuxueya.atcrowdfunding.potal.dao;

import java.util.Map;

import com.xuxueya.atcrowdfunding.bean.Member;
import com.xuxueya.atcrowdfunding.bean.MemberCert;

public interface MemberDao {

	Member queryMemberForLogin(Map paramMap);

	int updateAcctType(Member member);

	int doupdatebasicinfo(Member loginmember);

	int uploadimg(MemberCert memberCert);

	void updateEmail(String email);

	void upateAuthStatus(Member loginmember);

	Member queryMemberByid(Integer memberid);

}
