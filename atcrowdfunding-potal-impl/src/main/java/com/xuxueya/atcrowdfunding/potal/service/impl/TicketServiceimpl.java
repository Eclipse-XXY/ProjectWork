package com.xuxueya.atcrowdfunding.potal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuxueya.atcrowdfunding.bean.Ticket;
import com.xuxueya.atcrowdfunding.potal.dao.TicketDao;
import com.xuxueya.atcrowdfunding.potal.service.TicketService;
@Service
public class TicketServiceimpl implements TicketService {
	@Autowired
private TicketDao ticketDao;

	@Override
	public Ticket queryTicketByMemberId(Integer id) {
		return ticketDao.queryTicketByMemberId(id);
	}

	@Override
	public void saveTicket(Ticket ticket2) {
		ticketDao.saveTicket(ticket2);
	}

	@Override
	public int updateTicketProcessStep(Ticket ticket) {
		// TODO Auto-generated method stub
		return ticketDao.updateTicketProcessStep(ticket);
	}

	@Override
	public int updateTicket(Ticket ticket) {
		return ticketDao.updateTicket(ticket);
	}

	@Override
	public Ticket queryTicketByPIId(String piid) {
		return ticketDao.queryTicketByPIId(piid);
	}

	@Override
	public void updateTicketStatus(Integer memberId) {
		ticketDao.updateTicketStatus(memberId);
	}
}
