package com.cg.mts.entity;

import java.util.List;

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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "movie")
@ApiModel(description = "All details about the movie. ")
public class Movie
/**
 * Class Movie
 */
{
	@Id
	@SequenceGenerator(name = "movieSeqGen",sequenceName = "movieSeq",initialValue = 4001,allocationSize = 100)
	@GeneratedValue(generator = "movieSeqGen")
	
	@ApiModelProperty(notes = "The database generated movie ID")
	private long movieId;
	
	@ApiModelProperty(notes = "The  movie name")
	@NotNull(message="Please Enter Movie Name")
	@NotEmpty(message="Please Enter Customer Name. Name can not be blank.")
	private String movieName;
	
	@ApiModelProperty(notes = "The  movie genre")
	@NotNull(message="Please Enter Movie Genre")
	@NotEmpty(message="Please Enter Movie Genre. Genre can not be blank.")
	private String  movieGenre;
	
	@ApiModelProperty(notes = "The movie hours")
	@NotNull(message="Please Enter Movie Hours")
	@NotEmpty(message="Please Enter Movie Hours. Hours can not be blank.")
	private String movieHours;
	
	@ApiModelProperty(notes = "The language")
	@NotNull(message="Please Enter Movie language")
	@NotEmpty(message="Please Enter Movie language. language can not be blank.")
	private String language;

	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY, targetEntity = Theatre.class)
	@JoinColumn(name="theatre_id", insertable = false, updatable = false)
	private Theatre theatre;
	
	/**
	 * Parameterized constructor
	 * @Param movieName
	 * @Param mobile number
	 */
	public Movie( String movieName, String movieGenre, String movieHours, String language,
			Theatre theatre) {
		super();
		this.movieName = movieName;
		this.movieGenre = movieGenre;
		this.movieHours = movieHours;
		this.language = language;
		this.theatre = theatre;
	}
	/**
	 * Unparameterized constructor
	 */

	public Movie() {
		super();
	}
	/**
	 * 
	 * @return movieId
	 */

	public long getMovieId() {
		return movieId;
	}
	/**
	 * 
	 * @param movieId
	 */

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}
	/**
	 * 
	 * @return movieName
	 */

	public String getMovieName() {
		return movieName;
	}
	/**
	 * 
	 * @param movieName
	 */

	public void setMovieName( String movieName) {
		this.movieName = movieName;
	}
	
	/**
	 * 
	 * @return theatre
	 */

	public Theatre getTheatre() {
		return theatre;
	}
	/**
	 * 
	 * @param theatre
	 */

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}
	/**
	 * 
	 * @return movieGenre
	 */
	
	public String getMovieGenre() {
		return movieGenre;
	}
	/**
	 * 
	 * @param movieGenre
	 */
	
	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}
	/**
	 * 
	 * @return movieHours
	 */
	
	public String getMovieHours() {
		return movieHours;
	}
	/**
	 * 
	 * @param movieHours
	 */
	public void setMovieHours(String movieHours) {
		this.movieHours = movieHours;
	}
	/**
	 * 
	 * @return language
	 */
	
	public String getLanguage() {
		return language;
	}
	/**
	 * 
	 * @param language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}



	
}
	
	
