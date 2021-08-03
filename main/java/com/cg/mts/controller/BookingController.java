package com.cg.mts.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.entity.Booking;
import com.cg.mts.entity.Customer;
import com.cg.mts.entity.Movie;
import com.cg.mts.entity.Ticket;
import com.cg.mts.repository.IBookingRepository;
import com.cg.mts.repository.ICustomerRepository;
import com.cg.mts.repository.ITicketRepository;
import com.cg.mts.service.GlobalLoginService;
import com.cg.mts.service.IBookingService;
import com.cg.mts.service.ITicketService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/booking")
@Api(value = "Online Movie Ticket Booking System", description = "Operations pertaining to Booking in Online Movie Ticket Booking System")
@Validated
public class BookingController extends GlobalLoginService {
	/**
	 * Booking Controller
	 */

	@Autowired
	private IBookingService bookingService;

	@Autowired
	private ICustomerRepository customerRepository;

	@Autowired
	private IBookingRepository bookingRepository;

	@Autowired
	private ITicketService ticketService;

	@Autowired
	private ITicketRepository ticketRepository;

	
	/**
	 * 
	 * @param showId
	 * @return showBookingList by showId
	 */ @ApiOperation(value = "View list of booking", response = List.class)
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved list"),
	            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	        })
	@GetMapping("/getbookingbyshid/{shId}")
	public List<Booking> getBookingByShowId(@ApiParam(value = "Show Id from which booking object will be retrieved", required = true)@PathVariable("shId") long showId) {
		if(flag==1) {
		 return this.bookingService.showBookingList(showId);
		}
		return null;
	}
	
	/**
	 * 
	 * @param customerId
	 * @param booking
	 * @return saveBooking by customerId taking input
	 */
	 @ApiOperation(value = "Add Booking")
	@PostMapping("/addbookingbycustid/{custId}")
	public Optional<Object> addBookingByCustomerId(
			@ApiParam(value = "Customer Id for which booking wiil be stored in database table", required = true)@PathVariable("custId") long customerId,
			@ApiParam(value = "Booking object stored in database table", required = true)@Valid @RequestBody Booking booking) {
		if(flag==2) {
		 return this.customerRepository.findById(customerId).map(customer -> {
			booking.setCustomer(customer);
			return bookingRepository.save(booking);
		});
		}
		return null;
	}

	/**
	 * 
	 * @param ticket
	 * @param bookingId
	 * @param booking
	 * @return updateBooking by bookingId and ticketId
	 */
	 @ApiOperation(value = "Update booking")
	@PutMapping("/updatebookingbytid/{tId}/{bId}")
	public Booking updateBookingByTicketId(
			@ApiParam(value = "Ticket Id to update booking ", required = true) @PathVariable("tId") Ticket ticket, @ApiParam(value = "Booking Id to update booking object", required = true) @PathVariable("bId") long bookingId,
			@ApiParam(value = "Update booking object", required = true)@Valid @RequestBody Booking booking) {
		 if(flag==2) {
		Booking existingBooking = this.bookingService.updateBooking(booking, ticket, bookingId);
		existingBooking = this.updateBookingTotalAmount(existingBooking, bookingId);
		return existingBooking;
		 }
		 return null;
	}

	public Booking updateBookingTotalAmount(Booking booking, long bookingId) {
		if(flag==2) {
		Booking existingBooking = this.bookingService.updateBookingTotalAmount(booking, bookingId);
		return existingBooking;
		}
		return null;
	}

	/**
	 * 
	 * @param booking
	 * @param bookingId
	 * @return cancelBooking by bookingId
	 */
	 @ApiOperation(value = "Cancel Booking")
	@PutMapping("/cancelbookingbybid/{bookId}")
	public Booking cancelBooking(
			@ApiParam(value = "Update booking object", required = true)@Valid @RequestBody Booking booking, 
			 @ApiParam(value = "Booking Id from which booking object will be deleted from database table", required = true) @PathVariable("bookId") long bookingId) {
		 if(flag==2) {
		 Booking existingBooking = this.bookingService.cancelBooking(booking, bookingId);
		return existingBooking;
		 }
		 return null;
	}

	/**
	 * 
	 * @param movieId
	 * @return showBookings by movieId
	 */
	@ApiOperation(value = "Get booking by movieId")
	@GetMapping("/getbookingbymid/{mId}")
	public List<Booking> getBookingByMovieId(
			 @ApiParam(value = "Movie id from which booking object will be retrieved", required = true)@PathVariable("mId") @Positive long movieId) {
		if(flag==1) {
		return this.bookingService.showBookingsByMovieId(movieId);
	}
		return null;
	}

	

	/**
	 * 
	 * @param localdate
	 * @return showBooking by localDate
	 */
	@ApiOperation(value = "Get booking by date")
	@GetMapping("/getbookingbydate/{localdate}")
	public List<Booking> getBookingsByDate(
			 @ApiParam(value = "Local Date from which booking object will be retrieved", required = true)@RequestParam("localdate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localdate) {
		if(flag==1) {
		return this.bookingService.showBookingsByDate(localdate);
	}
		return null;
	}

	/**
	 * 
	 * @param customer
	 * @return booking by customer
	 */
	@ApiOperation(value = "Get booking by customerId")
	@GetMapping("/getbookingbycustid/{custId}")
	public List<Booking> getBookingByCustomer(
			@ApiParam(value = "Customer Id from which booking object will be retrieved", required = true)@PathVariable("custId") Customer customer) {
		if(flag==2) {
		return this.bookingService.findByCustomer(customer);
	}
		return null;
	}

}
