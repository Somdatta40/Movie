package com.cg.mts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.mts.entity.Admin;
import com.cg.mts.entity.Movie;
import com.cg.mts.entity.Theatre;
import com.cg.mts.exception.AdminNotFoundException;
import com.cg.mts.exception.MovieNotFoundException;
import com.cg.mts.repository.IMovieRepository;

@Service
public class MovieService implements IMovieService  {

	@Autowired
	private IMovieRepository movieRepository;
	/**
	 * add movie
	 */
	
	@Override
	public Movie addMovie(Movie movie) {
		// TODO Auto-generated method stub
		return this.movieRepository.save(movie);
	}
	/**
	 * update movie
	 */

	@Override
	public Movie updateMovie(Movie movie, long movieId) 
	{
		Movie existingMovie=this.movieRepository.findById(movieId).orElseThrow(()-> new MovieNotFoundException("Movie not found"+movieId));
		existingMovie.setMovieName(movie.getMovieName());
		existingMovie.setMovieGenre(movie.getMovieGenre());
		existingMovie.setLanguage(movie.getLanguage());
		existingMovie.setMovieHours(movie.getMovieHours());
		return this.movieRepository.save(existingMovie);
	}
	/**
	 * view movie by movieid
	 */

	
	@Override
	public Movie viewMovieById(long movieId) {
		return this.movieRepository.findById(movieId).orElseThrow(()-> new MovieNotFoundException("Movie not found with id"+movieId));
		
	}
	/**
	 * view all movie
	 */

	@Override
	public List<Movie> viewAllMovie() {
		return this.movieRepository.findAll();
	}
	/**
	 * find by theatre
	 */

	@Override
	public List<Movie> findByTheatre(Theatre theatre) {
		// TODO Auto-generated method stub
		return this.movieRepository.findByTheatre(theatre);
	}
	@Override
	public ResponseEntity<Movie> deleteMovie(long movieId) {
		// TODO Auto-generated method stub
		Movie existingMovie = this.movieRepository.findById(movieId)
				.orElseThrow(() -> new MovieNotFoundException("Movie not found with id" + movieId));
		this.movieRepository.delete(existingMovie);
		return ResponseEntity.ok().build();
	}
}