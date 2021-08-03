package com.cg.mts.entity;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "show")
@ApiModel(description = "All details about the show. ")
public class Show
/**
 * Class Show
 */

{

	@Id
	@SequenceGenerator(name = "showSeqGen", sequenceName = "showSeq", initialValue = 7001, allocationSize = 100)
	@GeneratedValue(generator = "showSeqGen")
	
	@ApiModelProperty(notes = "The database generated show ID")
	private long showId;
	
	@ApiModelProperty(notes = "The show start time")
	@JsonFormat(pattern="HH:mm")
	@NotNull(message="Please provide show start time.")
	private LocalTime showStartTime;
	
	@ApiModelProperty(notes = "The show end time")
	@JsonFormat(pattern="HH:mm")
	@NotNull(message="Please provide show end time.")
	private LocalTime showEndTime;
	
	@ApiModelProperty(notes = "The  show name")
	@NotNull(message="Please Enter Show Name")
	@NotEmpty(message="Please Enter Show Name. Show Name can not be blank.")
	private String showName;
	
	@ApiModelProperty(notes = "The database generated theatre ID")
	private long theatreId;

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY, targetEntity = Screen.class)
	@JoinColumn(name="screen_id", insertable = false, updatable = false)
	private Screen screen;

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY, targetEntity = Movie.class)
	@JoinColumn(name="movie_id", insertable = false, updatable = false)
	private Movie movie;

	/**
	 * Parameterized Constructor
	 * 
	 * @param showStartTime
	 * @param showEndTime
	 * @param showName
	 * @param theatreId
	 * @param movie
	 * @param screen
	 */
	public Show(LocalTime showStartTime, LocalTime showEndTime, String showName, long theatreId, Movie movie,
			Screen screen) {
		super();
		this.showStartTime = showStartTime;
		this.showEndTime = showEndTime;
		this.showName = showName;
		this.theatreId = theatreId;
		this.movie = movie;
		this.screen = screen;
	}

	/**
	 * Unparameterized Constructor
	 */
	public Show() {
		super();
	}

	/**
	 * 
	 * @return showId
	 */
	public long getShowId() {
		return showId;
	}

	/**
	 * 
	 * @param showId
	 */
	public void setShowId(long showId) {
		this.showId = showId;
	}

	/**
	 * 
	 * @return showStartTime
	 */
	public LocalTime getShowStartTime() {
		return showStartTime;
	}

	/**
	 * 
	 * @param showStartTime
	 */
	public void setShowStartTime(LocalTime showStartTime) {
		this.showStartTime = showStartTime;
	}

	/**
	 * 
	 * @return showEndTime
	 */
	public LocalTime getShowEndTime() {
		return showEndTime;
	}

	/**
	 * 
	 * @param showEndTime
	 */
	public void setShowEndTime(LocalTime showEndTime) {
		this.showEndTime = showEndTime;
	}

	/**
	 * 
	 * @return showName
	 */
	public String getShowName() {
		return showName;
	}

	/**
	 * 
	 * @param showName
	 */
	public void setShowName(String showName) {
		this.showName = showName;
	}

	/**
	 * 
	 * @return movie
	 */
	public Movie getMovie() {
		return movie;
	}

	/**
	 * 
	 * @param movie
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	/**
	 * 
	 * @return theatreId
	 */
	public long getTheatreId() {
		return theatreId;
	}

	/**
	 * 
	 * @param theatreId
	 */
	public void setTheatreId(long theatreId) {
		this.theatreId = theatreId;
	}

	/**
	 * 
	 * @return screen
	 */
	public Screen getScreen() {
		return screen;
	}

	/**
	 * 
	 * @param screen
	 */
	public void setScreen(Screen screen) {
		this.screen = screen;
	}

}