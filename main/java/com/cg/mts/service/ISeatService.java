package com.cg.mts.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.mts.entity.Seat;
import com.cg.mts.entity.Ticket;

public interface ISeatService {
	/**
	 * 
	 * @param seat
	 * @return bookseat
	 */
	public Seat bookSeat(Seat seat,Ticket ticket);
	/**
	 * 
	 * @return viewseats
	 */

	public List<Seat> viewSeats();
	/**
	 * 
	 * @param seatId
	 * @return cancelseatbooking
	 */

	public ResponseEntity<Seat> cancelSeatBooking(long seatId);
	/**
	 * 
	 * @param seat
	 * @param seatId
	 * @return blockseat
	 */

	public Seat blockSeat(Seat seat, long seatId); // not available for any booking
	/**
	 * 
	 * @param seatNumber
	 * @return findseatbyseatnumber
	 */

	public Seat findSeatBySeatNumber(String seatNumber);
	/**
	 * 
	 * @param ticket
	 * @return findbyticket
	 */

	public List<Seat> findByTicket(Ticket ticket);
}
