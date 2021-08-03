package com.cg.mts.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.mts.entity.Movie;
import com.cg.mts.entity.Screen;
import com.cg.mts.entity.Show;

public interface IShowRepository extends JpaRepository<Show, Long>{
	
    /**
     * 
     * @param showId
     * @return details of show by showId
     */
	public Show findByshowId(long showId);
	/**
	 * 
	 * @param theatreId
	 * @return details of show by theatreId
	 */
	public List<Show> findBytheatreId(long theatreId);
	/**
	 * 
	 * @param date
	 * @return details of show by date
	 */
	public List<Show> findByshowStartTime(LocalDate date);
	/**
	 * 
	 * @param screen
	 * @return details of show by screen
	 */
	public List<Show> findByScreen(Screen screen);
	/**
	 * @param movie
	 * @return details of show by movie
	 */
	public List<Show> findByMovie(Movie movie);
}
