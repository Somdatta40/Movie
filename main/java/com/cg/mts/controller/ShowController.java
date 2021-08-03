package com.cg.mts.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.entity.Booking;
import com.cg.mts.entity.Movie;
import com.cg.mts.entity.Screen;
import com.cg.mts.entity.Show;
import com.cg.mts.entity.Ticket;
import com.cg.mts.repository.IMovieRepository;
import com.cg.mts.repository.IScreenRepository;
import com.cg.mts.repository.IShowRepository;
import com.cg.mts.service.GlobalLoginService;
import com.cg.mts.service.IShowService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/show")
@Api(value = "Online Movie Ticket Booking System", description = "Operations pertaining to Show in Online Movie Ticket Booking System")
@Validated
public class ShowController extends GlobalLoginService {
	/**
	 * Show Controller
	 */

	@Autowired
	private IShowService showService;

	@Autowired
	private IShowRepository showRepository;

	@Autowired
	private IScreenRepository screenRepository;

	@Autowired
	private IMovieRepository movieRepository;
	
	/**
	 * 
	 * @return viewAllShows
	 */
	 @ApiOperation(value = "View list of all avaliable shows", response = List.class)
	    @ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    })
	@GetMapping("/getallshows")
	public List<Show> getAllShow() {
		 if(flag==1||flag==2) {
		return this.showService.viewAllShows();
		
		}
		 return null;
	}


	/**
	 * 
	 * @param movieId
	 * @param show
	 * @return
	 */
	 @ApiOperation(value = "Add show")
	@PostMapping("/addshowbymid/{mId}")
	public Optional<Object> addShowWithMId(
			@ApiParam(value = "Movie id from which show object will be retrieve", required = true)@PathVariable("mId") long movieId,
			@ApiParam(value = "Show object store in database table", required = true)@Valid@RequestBody Show show) {
		 if(flag==1) {
		 return this.movieRepository.findById(movieId).map(movie -> {
			show.setMovie(movie);
			return showService.saveShow(show);
		});
		 }
		 return null;
	}

	/**
	 * 
	 * @param show
	 * @param showid
	 * @return updateShow by showId
	 */
	 @ApiOperation(value = "Update show")
	@PutMapping("/updateshowbyid/{showId}")
	public Show updateShow(
			@ApiParam(value = "Update show object", required = true)@Valid @RequestBody Show show,
			 @ApiParam(value = "show Id to update show object", required = true)@PathVariable("showId") @Positive long showid) {
		 if(flag==1) {
		 Show existingshow = this.showService.updateShow(show, showid);
		return existingshow;
		 }
		 return null;
	}

	/**
	 * 
	 * @param showid
	 * @return removeShow by showId
	 */
	 @ApiOperation(value = "Delete show")
	@DeleteMapping("/deleteshowbyid/{showId}")
	public ResponseEntity<Show> removeShow(
			@ApiParam(value = "Show Id from which show object will be deleted from database table", required = true)@PathVariable("showId")@Positive long showid) {
		 if(flag==1) {
		 return this.showService.removeShow(showid);
		 }
		 return null;
	}

	/**
	 * 
	 * @param screen
	 * @return show by screen
	 */
	 @ApiOperation(value = "Get show by sreenId")
	@GetMapping("/getshowbyscid/{scId}")
	public List<Show> getShowByScreenId(
			@ApiParam(value = "screen id from which show object will be retrieved", required = true) @PathVariable("scId") Screen screen) {
		 if(flag==1) {
		 return this.showService.findByScreen(screen);
		 }
		 return null;
	}

	/**
	 * 
	 * @param movie
	 * @return show by movie
	 */
	 @ApiOperation(value = "Get show by movieId")
	@GetMapping("/getshowbymid/{mId}")
	public List<Show> getShowByMovieId(
			@ApiParam(value = "movie id from which show object will retrieve", required = true)@PathVariable("mId") Movie movie) {
		 if(flag==1) {
		 return this.showService.findByMovie(movie);
		 }
		 return null;
	}

	/**
	 * 
	 * @param showid
	 * @return view Show by showId
	 */
	 @ApiOperation(value = "Get show by showId")
	@GetMapping("/getshowbyid/{showId}")
	public Show getShowById(
			@ApiParam(value = "show id from which show object will be retrieved", required = true)@PathVariable("showId") int showid) {
		 if(flag==1) {
		 return this.showService.viewShow(showid);
		 }
		 return null;
	}


	/**
	 * 
	 * @param theatreid
	 * @return getshowList by theatreId
	 */
	 @ApiOperation(value = "Get show by theatreId")
	@GetMapping("/getshowbythid/{thId}")
	public List<Show> getShowListByTheatreId(
			@ApiParam(value = "ticket id from which show object will be retrieved", required = true)@PathVariable("thId") int theatreid) {
		 if(flag==1) {
		 return this.showService.viewShowList(theatreid);
		 }
		 return null;
	}

/**
 * 
 * @param screen
 * @param showId
 * @param show
 * @return updateShow
 */ @ApiOperation(value = "Update show by Id")
	@PutMapping("/updateshowbyscid/{scId}/{shId}")
	public Show updateShowByScreen(
			@ApiParam(value = "Screen Id to update show object", required = true)@PathVariable("scId") Screen screen, 
			@ApiParam(value = "show Id to update show object", required = true)@PathVariable("shId") Long showId,
			  @ApiParam(value = "Update show object", required = true)@Valid @RequestBody Show show) {
	 if(flag==1) {	
	 Show existingShow = this.showService.updateShowByScreen(show, screen, showId);
		return existingShow;
	 }
	 return null;
	}
}
