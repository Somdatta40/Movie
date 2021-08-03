package com.cg.mts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.mts.entity.Movie;
import com.cg.mts.entity.Seat;
import com.cg.mts.entity.Ticket;
import com.cg.mts.exception.MovieNotFoundException;
import com.cg.mts.exception.SeatNotFoundException;
import com.cg.mts.repository.ISeatRepository;

@Service
public class SeatService implements ISeatService {

	@Autowired
	private ISeatService seatService;
	@Autowired
	private ISeatRepository seatRepository;

	/**
	 * book seat
	 */
	@Override
	public Seat bookSeat(Seat seat,Ticket ticket) {
		// TODO Auto-generated method stub
		return this.seatRepository.save(seat);
	}

	/**
	 * cancel seat booking
	 */
	@Override
	public ResponseEntity<Seat> cancelSeatBooking(long seatId) {
		// TODO Auto-generated method stub
		Seat existingSeat = this.seatRepository.findById(seatId)
				.orElseThrow(() -> new SeatNotFoundException("Seat not found with id" + seatId));
		this.seatRepository.delete(existingSeat);
		return ResponseEntity.ok().build();
	}

	/**
	 * block seat
	 */
	@Override
	public Seat blockSeat(Seat seat, long seatId) {
		Seat existingSeat = this.seatRepository.findById(seatId)
				.orElseThrow(() -> new SeatNotFoundException("Seat not found" + seatId));
		existingSeat.setSeatStatus(seat.isSeatStatus());
		return this.seatRepository.save(existingSeat);
	}
	/**
	 * find seat by seatNumber
	 */
	@Override
	public Seat findSeatBySeatNumber(String seatNumber) {
		// TODO Auto-generated method stub
		return this.seatRepository.findSeatBySeatNumber(seatNumber);
	}

	/**
	 * view seats
	 */
	@Override
	public List<Seat> viewSeats() {
		// TODO Auto-generated method stub
		return this.seatRepository.findAll();
	}

	/**
	 * view seat by ticketId
	 */
	@Override
	public List<Seat> findByTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return this.seatRepository.findByTicket(ticket);
	}

}
