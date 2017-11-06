package com.xuxueya.atcrowdfunding.manager.service;

import java.util.List;
import java.util.Map;

public interface MemberCertService {

	List<Map<String, Object>> queryMemberCertbyMebId(Integer memberid);

}
