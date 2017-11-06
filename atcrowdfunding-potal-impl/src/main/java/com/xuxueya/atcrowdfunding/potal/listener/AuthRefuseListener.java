package com.xuxueya.atcrowdfunding.potal.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.context.ApplicationContext;

import com.xuxueya.atcrowdfunding.bean.Member;
import com.xuxueya.atcrowdfunding.potal.service.MemberService;
import com.xuxueya.atcrowdfunding.potal.service.TicketService;
import com.xuxueya.atcrowdfunding.util.ApplicationContextUilts;

public class AuthRefuseListener implements ExecutionListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		ApplicationContext ioc=(ApplicationContext) ApplicationContextUilts.applicationContext;
		MemberService memberService = ioc.getBean(MemberService.class);
		TicketService ticketService = ioc.getBean(TicketService.class);
		//更改memberstatus 为2 审核通过
		Integer memberId= (Integer) execution.getVariable("memberid");
		Member member = new Member();
		member.setId(memberId);
		member.setAuthstatus("0");
		memberService.upateAuthStatus(member);
		//更改流程表单的状态 status
		ticketService.updateTicketStatus(memberId);
	}
	

}
