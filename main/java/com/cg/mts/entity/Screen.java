package com.cg.mts.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "screen")
@ApiModel(description = "All details about the screen. ")
public class Screen
/**
 * Class Screen
 */

{
	@Id
	@SequenceGenerator(name = "screeneqGen", sequenceName = "screenSeq", initialValue = 5001, allocationSize = 100)
	@GeneratedValue(generator = "screenSeqGen")
	
	@ApiModelProperty(notes = "The database generated screen ID")
	private long screenId;
	
	@ApiModelProperty(notes = "The  screen name")
	@NotNull(message="Please Enter Screen Name")
	@NotEmpty(message="Please Enter Screen Name. Name can not be blank.")
	private String screenName;
	
	@ApiModelProperty(notes = "The  rows")
	@NotNull(message="Please Enter rows")
	@Column(name="screen_rows")
	private int rows;
	
	@ApiModelProperty(notes = "The  columns")
	@NotNull(message="Please Enter Columns")
	@Column(name="screen_columns")
	private int columns;

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY, targetEntity = Theatre.class)
	@JoinColumn(name="theatre_id", insertable = false, updatable = false)
	private Theatre theatre;

	/**
	 * Parameterized Constructor
	 * 
	 * @param screenName
	 * @param rows
	 * @param columns
	 * @param theatre
	 */
	public Screen(String screenName, int rows, int columns, Theatre theatre) {
		super();
		this.screenName = screenName;
		this.rows = rows;
		this.columns = columns;
		this.theatre = theatre;
	}

	/**
	 * Unparameterized Constructor
	 */
	public Screen() {
		super();
	}

	/**
	 * 
	 * @return screenId
	 */
	public long getScreenId() {
		return screenId;
	}

	/**
	 * 
	 * @param screenId
	 */
	public void setScreenId(long screenId) {
		this.screenId = screenId;
	}

	/**
	 * 
	 * @return screenName
	 */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * 
	 * @param screenName
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	/**
	 * 
	 * @return rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * 
	 * @param rows
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * 
	 * @return columns
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * 
	 * @param columns
	 */
	public void setColumns(int columns) {
		this.columns = columns;
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

}
