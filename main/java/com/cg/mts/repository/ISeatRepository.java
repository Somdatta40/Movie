package com.cg.mts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mts.entity.Seat;
import com.cg.mts.entity.Theatre;
import com.cg.mts.entity.Ticket;

@Repository
public interface ISeatRepository extends JpaRepository<Seat, Long> {

	/**
	 * 
	 * @param seatNumber
	 * @return seat by seatNumber
	 */
	public Seat findSeatBySeatNumber(String seatNumber);

	/**
	 * 
	 * @param ticket
	 * @return seat by ticket
	 */
	public List<Seat> findByTicket(Ticket ticket);
}
