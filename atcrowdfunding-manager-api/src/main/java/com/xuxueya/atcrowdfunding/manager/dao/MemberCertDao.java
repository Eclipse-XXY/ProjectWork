package com.xuxueya.atcrowdfunding.manager.dao;

import java.util.List;
import java.util.Map;

public interface MemberCertDao {

	List<Map<String, Object>> queryMemberCertbyMebId(Integer memberid);

}
