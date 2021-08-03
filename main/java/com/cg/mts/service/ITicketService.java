package com.cg.mts.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.mts.entity.Theatre;
import com.cg.mts.entity.Ticket;

public interface ITicketService {
	/**
	 * 
	 * @param ticket
	 * @return saveticket
	 */

	public Ticket saveTicket(Ticket ticket);
	/**
	 * 
	 * @param tiId
	 * @return delete ticket
	 */

	public ResponseEntity<Ticket> deleteTicket(long tiId) ;
	/**
	 * 
	 * @param tiId
	 * @return viewticket by Id
	 */
	public Ticket viewTicketById(long tiId) ;
	/**
	 * 
	 * @return viewallticket
	 */
	public List<Ticket> viewAllTicket();
	/**
	 * 
	 * @param screenName
	 * @return findbyscreenname
	 */
	public List<Ticket> findByScreenName(String screenName);
	/**
	 * 
	 * @param ticket
	 * @param ticketId
	 * @return updateticket
	 */
	public Ticket updateTicket(Ticket ticket, long ticketId);
	/**
	 * 
	 * @param theatre
	 * @return findbytheatre
	 */
	public List<Ticket> findByTheatre(Theatre theatre);
	
}