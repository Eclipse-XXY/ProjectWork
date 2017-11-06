package com.xuxueya.atcrowdfunding.potal.service;

import com.xuxueya.atcrowdfunding.bean.Ticket;

public interface TicketService {

	Ticket queryTicketByMemberId(Integer id);

	void saveTicket(Ticket ticket2);

	int updateTicketProcessStep(Ticket ticket);

	int updateTicket(Ticket ticket);

	Ticket queryTicketByPIId(String piid);

	void updateTicketStatus(Integer memberId);

}
