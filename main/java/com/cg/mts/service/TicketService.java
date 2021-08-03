package com.cg.mts.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.mts.entity.Theatre;
import com.cg.mts.entity.Ticket;
import com.cg.mts.exception.TicketNotFoundException;
import com.cg.mts.repository.ITicketRepository;

@Service
public class TicketService implements ITicketService{

	@Autowired
	private ITicketRepository ticketRepository;
	/**
	 * save ticket
	 */
	
	@Override
	public Ticket saveTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return this.ticketRepository.save(ticket);
	}
	/**
	 * update ticket by ticket id
	 */

	@Override
	public Ticket updateTicket(Ticket ticket,long tiId) {
		// TODO Auto-generated method stub
		Ticket existingTicket=this.ticketRepository.findById(tiId).orElseThrow(()-> new TicketNotFoundException("Ticket not found"+tiId));
		existingTicket.setTicketId(ticket.getTicketId());
		existingTicket.setScreenName(ticket.getScreenName());
		existingTicket.setNoOfSeats(ticket.getNoOfSeats());
		return this.ticketRepository.save(existingTicket);
	}
	/**
	 * view ticket by ticketId
	 */


	@Override

	public Ticket viewTicketById(long tiId) {
		// TODO Auto-generated method stub
		return this.ticketRepository.findById(tiId).orElseThrow(()-> new TicketNotFoundException("Ticket not found with id"+tiId));
	}
	/**
	 * view all ticket
	 */

	@Override
	public List<Ticket> viewAllTicket() {
		// TODO Auto-generated method stub
		return this.ticketRepository.findAll();
	}
	/**
	 * find by screen name
	 */

	@Override
	public List<Ticket> findByScreenName(String screenName) {
		// TODO Auto-generated method stub
		return this.ticketRepository.findByScreenName(screenName);
	}
	/**
	 * delete ticket by ticketId
	 */

	@Override
	public ResponseEntity<Ticket> deleteTicket(long tiId) {
		// TODO Auto-generated method stub
		Ticket existingTicket=this.ticketRepository.findById(tiId).orElseThrow(()-> new TicketNotFoundException("Ticket not found"+tiId));
		this.ticketRepository.delete(existingTicket);
		return ResponseEntity.ok().build();
	}
	/**
	 * find by theatre
	 */

	@Override
	public List<Ticket> findByTheatre(Theatre theatre) {
		// TODO Auto-generated method stub
		return this.ticketRepository.findByTheatre(theatre);
	}



	


}