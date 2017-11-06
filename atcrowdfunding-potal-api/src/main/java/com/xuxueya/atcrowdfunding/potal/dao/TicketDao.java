package com.xuxueya.atcrowdfunding.potal.dao;

import com.xuxueya.atcrowdfunding.bean.Ticket;

public interface TicketDao {

	Ticket queryTicketByMemberId(Integer id);

	void saveTicket(Ticket ticket2);

	int updateTicketProcessStep(Ticket ticket);

	int updateTicket(Ticket ticket);

	Ticket queryTicketByPIId(String piid);

	void updateTicketStatus(Integer memberId);

}
