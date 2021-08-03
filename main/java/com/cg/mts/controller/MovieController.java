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

import com.cg.mts.entity.Admin;
import com.cg.mts.entity.Movie;
import com.cg.mts.entity.Theatre;
import com.cg.mts.repository.IMovieRepository;
import com.cg.mts.repository.ITheatreRepository;
import com.cg.mts.service.GlobalLoginService;
import com.cg.mts.service.IMovieService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/movie")
@Api(value = "Online Movie Ticket Booking System", description = "Operations pertaining to Movie in Online Movie Ticket Booking System")
@Validated
public class MovieController extends GlobalLoginService

	/**
	 * Movie controller
	 */
{
	@Autowired
	private IMovieService movieService;
	
	@Autowired
	private IMovieRepository movieRepository;
	
	@Autowired
	private ITheatreRepository theatreRepository;
	
	/**
	 * 
	 * @return all movies
	 */
	 @ApiOperation(value = "View list of available movies", response = List.class)
	    @ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    })
	@GetMapping("/getallmovies")
	public List<Movie> getAllMovies()
	{
		 if(flag==1||flag==2) {
		return this.movieService.viewAllMovie();
		 }
		 return null;
	}
	 
	/**
	 * 
	 * @param movieId
	 * @return movie by movieId
	 */
	 @ApiOperation(value = "Get movie by Id")
	@GetMapping("/getmoviebyid/{movieId}")
	public Movie getmovieById(
			@ApiParam(value = "Movie id from which movie object will be retrieved", required = true)@PathVariable("movieId")long movieId)
	{
		 if(flag==1||flag==2) {
		return this.movieService.viewMovieById(movieId);
		 }
		 return null;
	}
	/**
	 * 
	 * @param theatre
	 * @return movie by theatreId
	 */
 
	@ApiOperation(value = "Get movie by theatreId")
	@GetMapping("/getmoviebythid/{thId}")
	public List<Movie> getmovieByTheatre(
		@ApiParam(value = "Theatre id from which movie object will be retrieved", required = true)@PathVariable("thId")Theatre theatre)
	{
		 if(flag==1||flag==2) {
		return this.movieService.findByTheatre(theatre);
		 }
		 return null;
	}
	/**
	 * 
	 * @param theatreId
	 * @param movie
	 * @return add movie by theatre Id
	 */
	@ApiOperation(value = "Add movie by theatre Id")
	@PostMapping("/addmoviebythid/{thId}")
	public Optional<Object> saveOrder(
			@ApiParam(value = "Ticket Id for which movie object will be stored in database table", required = true)@PathVariable("thId")long theatreId,
			@ApiParam(value = "movie object store in database table", required = true)@Valid @RequestBody Movie movie)
	{
		if(flag==1) {
		return this.theatreRepository.findById(theatreId).map(theatre->{movie.setTheatre(theatre); 
		return movieRepository.save(movie);
		});
	}
	return null;
	}
	/**
	 * 
	 * @param movie
	 * @param movieId
	 * @return update movie by movie Id
	 */
	 @ApiOperation(value = "Update movie")
	@PutMapping("/updatemoviebyid/{movieId}")
	public Movie updateMovie(
			 @ApiParam(value = "Update movie object", required = true)@Valid @RequestBody Movie movie,
			  @ApiParam(value = "movie Id to update movie object", required = true)@PathVariable("movieId")long movieId)
	{
		 if(flag==1) {
		Movie existingMovie= this.movieService.updateMovie(movie, movieId);
		return existingMovie;
		 }
		 return null;
	}

		/**
		 * 
		 * @param movieId
		 * @return deleteMovie by movieId
		 */
		@ApiOperation(value = "Delete a movie")
		@DeleteMapping("/deletemoviebyid/{mId}")
		public ResponseEntity<Movie> deleteMovie(
				@ApiParam(value = "Movie Id  from which movie object will be deleted from database table", required = true) @PathVariable("mId") long movieId) {
			if(flag==1) {
			return this.movieService.deleteMovie(movieId);
			}
			return null;
		}

}
