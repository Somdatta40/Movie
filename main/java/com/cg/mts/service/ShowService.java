package com.cg.mts.service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.mts.entity.Booking;
import com.cg.mts.entity.Movie;
import com.cg.mts.entity.Screen;
import com.cg.mts.entity.Show;
import com.cg.mts.exception.BookingNotFoundException;
import com.cg.mts.exception.ShowNotFoundException;
import com.cg.mts.repository.IShowRepository;

@Service
public class ShowService implements IShowService {

	@Autowired
	private IShowRepository showRepository;
	

	/**
	 * add show
	 */
	@Override
	public Show addShow(Show show) {
		// TODO Auto-generated method stub
		return this.showRepository.save(show);
	}

	/**
	 * update show by showId
	 */
	@Override
	public Show updateShow(Show show, long showId) {
		Show existingShow = this.showRepository.findById(showId)
				.orElseThrow(() -> new ShowNotFoundException("Show not found" + showId));
		existingShow.setMovie(show.getMovie());
		existingShow.setShowStartTime(show.getShowStartTime());
		existingShow.setShowEndTime(show.getShowEndTime());
		existingShow.setShowName(show.getShowName());
		existingShow.setTheatreId(show.getTheatreId());
		return this.showRepository.save(existingShow);
	}

	/**
	 * remove show by showId
	 */
	@Override
	public ResponseEntity<Show> removeShow(long showId) {
		Show existingShow = this.showRepository.findById(showId)
				.orElseThrow(() -> new ShowNotFoundException("Show not found" + showId));
		this.showRepository.delete(existingShow);
		return ResponseEntity.ok().build();
	}

	/**
	 * view show by showId
	 */
	@Override
	public Show viewShow(long showId) {
		// TODO Auto-generated method stub
		return this.showRepository.findByshowId(showId);
	}

	/**
	 * view all shows
	 */
	@Override
	public List<Show> viewAllShows() {
		// TODO Auto-generated method stub
		return this.showRepository.findAll();
	}

	/**
	 * view show by theatreId
	 */
	@Override
	public List<Show> viewShowList(long theatreId) {
		// TODO Auto-generated method stub
		return this.showRepository.findBytheatreId(theatreId);
	}

	/**
	 * view show by date
	 */
	@Override
	public List<Show> viewShowList(LocalDate date) {
		// TODO Auto-generated method stub
		return this.showRepository.findByshowStartTime(date);
	}

	/**
	 * view show by screen
	 */
	@Override
	public List<Show> findByScreen(Screen screen) {
		return this.showRepository.findByScreen(screen);
	}

	/**
	 * view show by movie
	 */
	@Override
	public List<Show> findByMovie(Movie movie) {
		// TODO Auto-generated method stub
		return this.showRepository.findByMovie(movie);
	}

	@Override
	public Show updateShowByScreen(Show show, Screen screen, long showId) {
		// TODO Auto-generated method stub
		Show existingShow = this.showRepository.findById(showId)
				.orElseThrow(() -> new ShowNotFoundException("Booking not found" + showId));
		existingShow.setScreen(screen);
		return this.showRepository.save(existingShow);
	}

	@Override
	public Show saveShow( Show show) {
		// TODO Auto-generated method stub
		return this.showRepository.save(show);
	}

}
