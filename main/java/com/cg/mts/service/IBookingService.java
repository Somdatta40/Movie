package com.cg.mts.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.mts.entity.Booking;
import com.cg.mts.entity.Customer;
import com.cg.mts.entity.Ticket;

public interface IBookingService {
	/**
	 * 
	 * @param booking
	 * @return booking
	 */
	public Booking addBooking(Booking booking);
	/**
	 * 
	 * @param showId
	 * @return showId
	 */
	public List<Booking> showBookingList(long showId);
	/**
	 * 
	 * @param booking
	 * @param bookingId
	 * @return bookingId
	 */
	public Booking cancelBooking(Booking booking,long bookingId);
	/**
	 * 
	 * @param movieId
	 * @return movieId
	 */
	public List<Booking> showBookingsByMovieId(long movieId);
	/**
	 * 
	 * @param bookingdate
	 * @return booking date
	 */
	public List<Booking> showBookingsByDate(LocalDate bookingdate);
	/**
	 * s
	 * @param customer
	 * @return customer
	 */
	public List<Booking> findByCustomer(Customer customer);
	/**
	 * 
	 * @param booking
	 * @param ticket
	 * @param bookingId
	 * @return updatebooking
	 */
	public Booking updateBooking(Booking booking, Ticket ticket,long bookingId);
	/**
	 * 
	 * @param booking
	 * @param bookingId
	 * @return update booking total amount
	 */
	public Booking updateBookingTotalAmount(Booking booking, long bookingId);

}
