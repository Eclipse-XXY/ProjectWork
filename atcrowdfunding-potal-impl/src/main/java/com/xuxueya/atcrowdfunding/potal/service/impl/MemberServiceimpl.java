package com.xuxueya.atcrowdfunding.potal.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuxueya.atcrowdfunding.bean.Member;
import com.xuxueya.atcrowdfunding.bean.MemberCert;
import com.xuxueya.atcrowdfunding.potal.dao.MemberDao;
import com.xuxueya.atcrowdfunding.potal.service.MemberService;
@Service
public class MemberServiceimpl implements MemberService {
	@Autowired
private MemberDao memberDao;
	@Override
	public Member queryMemberForLogin(Map paramMap) {
		
		return memberDao.queryMemberForLogin(paramMap);
	}
	@Override
	public int updateAcctType(Member member) {
		return memberDao.updateAcctType(member);
	}
	@Override
	public int doupdatebasicinfo(Member loginmember) {
		return memberDao.doupdatebasicinfo(loginmember);
	}
	/**
	 * 三种批量删除我选择第二种删除就是在业务层循环调用数据库的dao层
	 * @param
	 * @author 徐雪娅
	 */
	@Override
	public int uploadimg(List<MemberCert> memberCerts) {
		int totalcount=0;
	for (MemberCert memberCert : memberCerts) {
	 totalcount= memberDao.uploadimg(memberCert);
	}
	return totalcount>0?1:0;
	}
	@Override
	public void updateEmail(String email) {
		memberDao.updateEmail(email);
	}
	@Override
	public void upateAuthStatus(Member loginmember) {
		memberDao.upateAuthStatus(loginmember);
	}
	@Override
	public Member queryMemberByid(Integer memberid) {
		return memberDao.queryMemberByid(memberid);
	}

}
