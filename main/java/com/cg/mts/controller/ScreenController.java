package com.cg.mts.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.cg.mts.entity.Screen;
import com.cg.mts.entity.Theatre;
import com.cg.mts.repository.IScreenRepository;
import com.cg.mts.repository.ITheatreRepository;
import com.cg.mts.service.GlobalLoginService;
import com.cg.mts.service.IScreenService;
import com.cg.mts.service.ITheatreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/screen")
@Api(value = "Online Movie Ticket Booking System", description = "Operations pertaining to Screen in Online Movie Ticket Booking System")
@Validated
public class ScreenController extends GlobalLoginService
/**
 * Screen Controller
 */

{
	@Autowired
	private IScreenService screenService;

	@Autowired
	private IScreenRepository screenRepository;

	@Autowired
	private ITheatreRepository theatreRepository;

	/**
	 * 
	 * @return AllScreenDetails
	 */
	 @ApiOperation(value = "View list of screen details", response = List.class)
	    @ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    })
	@GetMapping("/getallscreensdetails")
	public List<Screen> getAllScreens() {
		 if(flag==1) {
		return this.screenService.viewAllScreenDetails();
		 }
		 return null;
	}

	/**
	 * 
	 * @param theatreId
	 * @param screen
	 * @return save ScreenDetails by theatreId 
	 * taking input
	 */
	 @ApiOperation(value = "Add Screen details")
	@PostMapping("/addScreenbythid/{thId}")
	public Optional<Object> saveScreen(
			@ApiParam(value = "theatre Id to add screen object", required = true)@PathVariable("thId") long theatreId,
			@ApiParam(value = "screen object store in database table", required = true)@Valid @RequestBody Screen screen) {
		 if(flag==1) {
		 return this.theatreRepository.findById(theatreId).map(theatre -> {
			screen.setTheatre(theatre);
			return screenRepository.save(screen);
		});
		 }
		 return null;
	}


	/**
	 * 
	 * @param theatre
	 * @return Screen by Theatre
	 */
	 @ApiOperation(value = "Get screen by theatreId")
	@GetMapping("/getscreenbythid/{thId}")
	public List<Screen> getScreenByTheatre(
			@ApiParam(value = "Theatre id from which screen object will retrieve", required = true)@PathVariable("thId") Theatre theatre) {
		 if(flag==1) {
		 return this.screenRepository.findByTheatre(theatre);
		 }
		 return null;
	}

	/**
	 * 
	 * @param screen
	 * @param screenId
	 * @return updateScreenDetails by screenId
	 */
	 @ApiOperation(value = "Update screen")
	@PutMapping("/updatescreensdetailsbyid/{scrId}")
	public Screen updateOrder(
			@ApiParam(value = "Update screen object", required = true)@Valid @RequestBody Screen screen, 
			@ApiParam(value = "screen Id to update screen object", required = true)@PathVariable("scrId") long screenId) {
		 if(flag==1) {
		 Screen existingOrder = this.screenService.updateScreenDetails(screen, screenId);
		return existingOrder;
		 }
		 return null;
	}

	/**
	 * 
	 * @param screenId
	 * @return deleteScreenDetails by screenId
	 */
	 @ApiOperation(value = "Delete Screen")
	@DeleteMapping("/deletescreensdetailsbyid/{scrId}")
	public ResponseEntity<Screen> deleteOrder(
			@ApiParam(value = "Screen Id from which screen object will be deleted from database table", required = true) @PathVariable("scrId") long screenId) {
		 if(flag==1) {
		 return this.screenService.deleteScreenDetails(screenId);
		 }
		 return null;
	}
}
