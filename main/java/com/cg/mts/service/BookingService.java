package com.cg.mts.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mts.entity.Booking;
import com.cg.mts.entity.Customer;
import com.cg.mts.entity.Ticket;
import com.cg.mts.exception.BookingNotFoundException;
import com.cg.mts.repository.IBookingRepository;

@Service
public class BookingService implements IBookingService {

	@Autowired
	private IBookingRepository bookingRepository;

	/**
	 * add booking
	 */
	@Override
	public Booking addBooking(Booking booking) {
		return this.bookingRepository.save(booking);
	}

	/**
	 * cancel booking
	 */
	@Override
	public Booking cancelBooking(Booking booking, long bookingId) {
		Booking existingBooking = this.bookingRepository.findById(bookingId)
				.orElseThrow(() -> new BookingNotFoundException("Booking not found" + bookingId));
		existingBooking.setStatus(booking.getStatus());
		return this.bookingRepository.save(existingBooking);
	}

	/**
	 * update booking
	 */
	@Override
	public Booking updateBooking(Booking booking, Ticket ticket, long bookingId) {
		// TODO Auto-generated method stub
		Booking existingBooking = this.bookingRepository.findById(bookingId)
				.orElseThrow(() -> new BookingNotFoundException("Booking not found" + bookingId));
		existingBooking.setTicket(ticket);
		return this.bookingRepository.save(existingBooking);
	}

	/**
	 * show booking by movieId
	 */
	@Override
	public List<Booking> showBookingsByMovieId(long movieId) {
		// TODO Auto-generated method stub
		return this.bookingRepository.findBymovieId(movieId);
	}

	/**
	 * show booking by date
	 */
	@Override
	public List<Booking> showBookingsByDate(LocalDate bookingdate) {
		// TODO Auto-generated method stub
		return this.bookingRepository.findBybookingDate(bookingdate);
	}

	/**
	 * show booking by showId
	 */
	@Override
	public List<Booking> showBookingList(long showId) {
		// TODO Auto-generated method stub
		return this.bookingRepository.findByshowId(showId);
	}

	/**
	 * show booking by customer
	 */
	@Override
	public List<Booking> findByCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return this.bookingRepository.findByCustomer(customer);
	}

	/**
	 * update BookingTotalAmount
	 */
	@Override
	public Booking updateBookingTotalAmount(Booking booking, long bookingId) {
		// TODO Auto-generated method stub
		Booking existingBooking = this.bookingRepository.findById(bookingId)
				.orElseThrow(() -> new BookingNotFoundException("Booking not found" + bookingId));
		existingBooking.setTotalAmount(booking.getTicket().getTotalCost());
		return this.bookingRepository.save(existingBooking);
	}

}
