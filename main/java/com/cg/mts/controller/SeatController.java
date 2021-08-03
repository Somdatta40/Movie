package com.cg.mts.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.entity.Seat;
import com.cg.mts.entity.Ticket;
import com.cg.mts.repository.ISeatRepository;
import com.cg.mts.repository.ITicketRepository;
import com.cg.mts.service.GlobalLoginService;
import com.cg.mts.service.ISeatService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/seat")
@Api(value = "Online Movie Ticket Booking System", description = "Operations pertaining to Seat in Online Movie Ticket Booking System")
@Validated
public class SeatController extends GlobalLoginService{
	/**
	 * Seat Controller
	 */

	@Autowired
	private ISeatService seatService;

	@Autowired
	private ISeatRepository seatRepository;

	@Autowired
	private ITicketRepository ticketRepository;
	
	/**
	 * 
	 * @return all seats
	 */
	  @ApiOperation(value = "View list of seats", response = List.class)
	    @ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    })
	@GetMapping("/getallseats")
	public List<Seat> getAllSeats() {
		  if(flag==1||flag==2) {
		return this.seatService.viewSeats();
		  }
		  return null;
	}

	/**
	 * 
	 * @param ticketId
	 * @param seat
	 * @return save seat by ticketId
	 */
	  @ApiOperation(value = "Add seat")
	@PostMapping("/addseatbytid/{tId}")
	public Optional<Object> addseatbytId(
			@ApiParam(value = "Ticket id from which seat object will retrieve", required = true)@PathVariable("tId") @Positive long ticketId, 
			@ApiParam(value = "seat object store in database table", required = true)@Valid @RequestBody Seat seat) {
		  if(flag==2) {
		  return this.ticketRepository.findById(ticketId).map(ticket -> {
			seat.setTicket(ticket);
			return seatService.bookSeat(seat, ticket);
		});
		  }
		  return null;
	}

	/**
	 * 
	 * @param ticket
	 * @return seat by ticketId
	 */
	  @ApiOperation(value = "Get seat by Id")
	@GetMapping("/getseatbytid/{tId}")
	public List<Seat> getAllSeatBytId(
		@ApiParam(value = "Ticket id from which seat object will be retrieved", required = true)@PathVariable("tId") Ticket ticket) {
		  if(flag==2) {
		  return this.seatService.findByTicket(ticket);
		  }
		  return null;
	}

	  /**
		 * 
		 * @param seat number
		 * @return seat by seat number
		 */
		  @ApiOperation(value = "Get seat by seatNumber")
		@GetMapping("/getseatbyseatnumber/{seatNumber}")
		public Seat getSeatBySeatNumber(
			@ApiParam(value = "Seat number from which seat object will be retrieved", required = true)@PathVariable("seatNumber") String seatNumber) {
			  if(flag==2||flag==1) {
			  return this.seatService.findSeatBySeatNumber(seatNumber);
			  }
			  return null;
		}
	
	/**
	 * 
	 * @param seat
	 * @param seatId
	 * @return updateSeat by seatId
	 */
	 @ApiOperation(value = "Update seat")
	@PutMapping("/updateseatbyid/{sId}")
	public Seat updateSeat(
			@RequestBody Seat seat,
			@ApiParam(value = "seat Id to update seat object", required = true)@PathVariable("sId")@Positive long seatId) {
		 if(flag==1) {
		 return this.seatService.blockSeat(seat, seatId);
		 }
		 return null;
	}

	/**
	 * 
	 * @param seatId
	 * @return cancelSeatBooking by seatId
	 */
	 @ApiOperation(value = "Delete seat")
	@DeleteMapping("/deleteseatbyid/{sId}")
	public ResponseEntity<Seat> deleteSeat(
			@ApiParam(value = "seat Id from which seat object will be deleted from database table", required = true)@PathVariable("sId")@Positive long seatId) {
		 if(flag==2) {
		 return this.seatService.cancelSeatBooking(seatId);
		 }
		 return null;
	}
}
