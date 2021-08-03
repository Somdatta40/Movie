package com.cg.mts.service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.cg.mts.entity.Booking;
import com.cg.mts.entity.Movie;
import com.cg.mts.entity.Screen;
import com.cg.mts.entity.Show;
import com.cg.mts.entity.Ticket;

public interface IShowService {
	/**
	 * 
	 * @param show
	 * @return addshow
	 */

	public Show addShow(Show show);
	/**
	 * 
	 * @param show
	 * @param showId
	 * @return updateshow
	 */

	public Show updateShow(Show show, long showId);
	/**
	 * 
	 * @param showId
	 * @return viewshow
	 */

	public Show viewShow(long showId);
	/**
	 * 
	 * @return allshow
	 */

	public List<Show> viewAllShows();
	/**
	 * 
	 * @param theatreId
	 * @return showlist
	 */

	public List<Show> viewShowList(long theatreId);
	/**
	 * 
	 * @param date
	 * @return viewshowlist
	 */

	public List<Show> viewShowList(LocalDate date);
	/**
	 * 
	 * @param showId
	 * @return removeshow
	 */

	public ResponseEntity<Show> removeShow(long showId);
	/**
	 * 
	 * @param screen
	 * @return find by screen
	 */

	public List<Show> findByScreen(Screen screen);
	/**
	 * 
	 * @param movie
	 * @return find by movie
	 */

	public List<Show> findByMovie(Movie movie);
	
	/**
	 * 
	 * @param show
	 * @param screen
	 * @param showId
	 * @return updated show details
	 */
	public Show updateShowByScreen(Show show, Screen screen,long showId);
	
	/**
	 * 
	 * @param show
	 * @return show
	 */
	public Show saveShow( Show show);
}
