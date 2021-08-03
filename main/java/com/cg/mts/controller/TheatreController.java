
package com.cg.mts.controller;

import java.util.List;

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
import com.cg.mts.service.GlobalLoginService;
import com.cg.mts.service.ITheatreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/theatre")
@Api(value = "Online Movie Ticket Booking System", description = "Operations pertaining to Theatre in Online Movie Ticket Booking System")
@Validated
public class TheatreController extends GlobalLoginService
/**
 * Theatre Controller
 */

	{
	@Autowired 
	private ITheatreService theatreService;
	/**
	 * 
	 * @return all theatre
	 */
	@ApiOperation(value = "View list of theatre", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@GetMapping("/getalltheatres")
	public List<Theatre> getAlltheatre(){
		if(flag==1||flag==2) {
		return this.theatreService.viewAllTheatre();
		}
		return null;
	}
	/**
	 * 
	 * @param theatreId
	 * @return theatre by theatreId
	 */
	 @ApiOperation(value = "Get an theatre by Id")
	@GetMapping("/gettheatrebyid/{thId}")
	public Theatre getTheatreById(
			@ApiParam(value = "Thaetre id from which thaetre object will be retrieved", required = true)@PathVariable("thId") @Positive long theatreId){
		 if(flag==1) {
		 return this.theatreService.viewTheatreById(theatreId);
		 }
		 return null;
	}
	/**
	 * 
	 * @param theatreName
	 * @return theatre name
	 */
	 @ApiOperation(value = "Get an theatre by name")
	@GetMapping("/getbytheatrebyname/{thname}")
	public List<Theatre> getTheatreByName(
			@ApiParam(value = "Theatre name from which thaetre object will be retrieved", required = true)@PathVariable("thname") String theatreName){
		 if(flag==1||flag==2) {
		 return this.theatreService.findByTheatreName(theatreName);
		 }
		 return null;
	}
	/**
	 * 
	 * @param theatre
	 * @return add theatre
	 */
	 @ApiOperation(value = "Add thaatre")
	@PostMapping("/addtheatre")
	public Theatre saveTheatre(
			@ApiParam(value = "Theatre object stored in database table", required = true)@Valid @RequestBody Theatre theatre) {
		 if(flag==1) {
		 return this.theatreService.saveTheatre(theatre);
		 }
		 return null;
	}
	/**
	 * 
	 * @param theatre
	 * @param theatreId
	 * @return update theatre by theatreId
	 */
	 @ApiOperation(value = "Update Theatre")
	@PutMapping("/updatetheatrebyid/{thId}")
	public Theatre updateTheatre(
			@ApiParam(value = "Update theatre object", required = true)@Valid@RequestBody Theatre theatre,
			 @ApiParam(value = "Theatre Id to update theatre object", required = true)@PathVariable("thId")@Positive long theatreId) {
		 if(flag==1) {
		 return this.theatreService.updateTheatre(theatre, theatreId);      
		 }
		 return null;
	}
	/**
	 * 
	 * @param theatreId
	 * @return delete theatre by theatreId
	 */

	 @ApiOperation(value = "Delete theatre")
	@DeleteMapping("/deletetheatrebyid/{thId}")
	public ResponseEntity<Theatre> deleteTheatre(
			@ApiParam(value = "Theatre Id from which theatre object will be deleted from database table", required = true) @PathVariable("thId")long theatreId){
		 if(flag==1) {
		 return this.theatreService.deleteTheatre(theatreId);
		 }
		 return null;
	}
}