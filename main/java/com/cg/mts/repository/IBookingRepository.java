package com.cg.mts.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.mts.entity.Booking;
import com.cg.mts.entity.Customer;
import com.cg.mts.entity.Ticket;

public interface IBookingRepository extends JpaRepository<Booking, Long> {
	/**
	 * 
	 * @param movieId
	 * @return details of booking by movieId
	 */
	public List<Booking> findBymovieId(long movieId);
	/**
	 * 
	 * @param bookingdate
	 * @return details of booking by booking date
	 */

	public List<Booking> findBybookingDate(LocalDate bookingdate);
	/**
	 * 
	 * @param showId
	 * @return details of booking by showId
	 */

	public List<Booking> findByshowId(long showId);
	/**
	 * 
	 * @param customer
	 * @return details of booking by customer
	 */

	public List<Booking> findByCustomer(Customer customer);
}
