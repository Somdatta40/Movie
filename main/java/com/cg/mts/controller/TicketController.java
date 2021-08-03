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

import com.cg.mts.entity.Theatre;
import com.cg.mts.entity.Ticket;
import com.cg.mts.repository.ITheatreRepository;
import com.cg.mts.repository.ITicketRepository;
import com.cg.mts.service.GlobalLoginService;
import com.cg.mts.service.ITicketService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/ticket")
@Api(value = "Online Movie Ticket Booking System", description = "Operations pertaining to Ticket in Online Movie Ticket Booking System")
@Validated
public class TicketController extends GlobalLoginService
/**
 * Ticket Controller
 */


{
	
	@Autowired 
	private ITheatreRepository theatreRepository;
	
	@Autowired
	private ITicketRepository ticketRepository;
	

	@Autowired 
	private ITicketService ticketService;
	/**
	 * 
	 * @return get all ticket
	 */ @ApiOperation(value = "View list of tickets", response = List.class)
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved list"),
	            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	        })
	
	@GetMapping("/getalltickets")
	public List<Ticket> getAllticket(){
		 if(flag==1) {
		return this.ticketService.viewAllTicket();
		 }
		 return null;
	}
	
	/**
	 * 
	 * @param screenName
	 * @return ticket by scName
	 */
	 @ApiOperation(value = "Get Ticket by screenName ")
	@GetMapping("/getticketbyscname/{scName}")
	public List<Ticket> getTicketByScreenName(
			@ApiParam(value = "Screen name from which ticket object will be retrieved", required = true)@PathVariable("scName") String screenName){
		 if(flag==1||flag==2) {
		 return this.ticketService.findByScreenName(screenName);
		 }
		 return null;
	}
	/**
	 * 
	 * @param theatre
	 * @return get ticket by theatre Id
	 */
	 @ApiOperation(value = "Get ticket by thId")
	@GetMapping("/getticketbythid/{thId}")
	public List<Ticket> getTicketByTheatre(
			@ApiParam(value = "Ticket id from which ticket object will be retrieved", required = true)@PathVariable("thId") Theatre theatre){
		 if(flag==1) {
		 return this.ticketService.findByTheatre(theatre);
		 }
		 return null;
	}
	/**
	 * 
	 * @param ticket
	 * @param theatreId
	 * @return add theatre by theatreId
	 */
	 @ApiOperation(value = "Add ticket")
	@PostMapping("/addticketbythid/{thId}")
	public Optional<Object> saveTicket(
			 @ApiParam(value = "ticket object will be stored in database table", required = true)@Valid @RequestBody Ticket ticket,
			 @ApiParam(value = "theatre Id to add ticket object", required = true)@PathVariable("thId")@Positive long theatreId) {
		 if(flag==2) {
		 return this.theatreRepository.findById(theatreId).map(theatre->{ticket.setTheatre(theatre);
		return ticketRepository.save(ticket);
		});
		 }
		 return null;
	}
	/**
	 * 
	 * @param ticket
	 * @param ticketId
	 * @return update ticket by ticketId
	 */
	 @ApiOperation(value = "Update ticket")
	@PutMapping("/updateticketbyid/{tId}")
	public Ticket updateTicket(
			@ApiParam(value = "Update ticket object", required = true)@Valid@RequestBody Ticket ticket,
			@ApiParam(value = "Ticket Id to update ticket object", required = true)@PathVariable("tId")long ticketId) {
		 if(flag==2||flag==1) {
		 return this.ticketService.updateTicket(ticket, ticketId);       
		 }
		 return null;
	}
	/**
	 * 
	 * @param ticketId
	 * @return delete ticket by ticketId
	 */
	 @ApiOperation(value = "Delete Ticket")
	@DeleteMapping("/deleteticketbyid/{tId}")
	public ResponseEntity<Ticket> deleteTicket(
			 @ApiParam(value = "Ticket Id from which ticket object will be deleted from database table", required = true)@PathVariable("tId") @Positive long ticketId){
		 if(flag==2) {
		 return this.ticketService.deleteTicket(ticketId);
		 }
		 return null;
	}
}