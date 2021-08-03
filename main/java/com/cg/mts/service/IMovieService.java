package com.cg.mts.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.mts.entity.Movie;
import com.cg.mts.entity.Theatre;

public interface IMovieService {
	/**
	 * 
	 * @param movie
	 * @return addmovie
	 */
	public Movie addMovie(Movie movie);
	/**
	 * 
	 * @param movie
	 * @param movieId
	 * @return update movie
	 */
	public Movie updateMovie(Movie movie,long movieId);
	/**
	 * 
	 * @param movieId
	 * @return viewmovieby Id
	 */
	
	public Movie viewMovieById(long movieId);
	/**
	 * 
	 * @return viewallmovie
	 */
	public List<Movie> viewAllMovie();
	/**
	 * 
	 * @param theatre
	 * @return find by theatre
	 */
	public List<Movie> findByTheatre(Theatre theatre);
	/**
	 * 
	 * @param movieId
	 * @return deleted movie
	 */
	public ResponseEntity<Movie> deleteMovie(long movieId);
	
}

