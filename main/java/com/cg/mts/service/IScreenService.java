package com.cg.mts.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.mts.entity.Screen;
import com.cg.mts.entity.Seat;
import com.cg.mts.entity.Theatre;

public interface IScreenService {
	/**
	 * 
	 * @param screen
	 * @return addscreen details
	 */
	public Screen addScreenDetails(Screen screen);
	/**
	 * 
	 * @param screen
	 * @param screenId
	 * @return updateScreen details
	 */

	public Screen updateScreenDetails(Screen screen, long screenId);
	/**
	 * 
	 * @param screenId
	 * @return delete screen details
	 */

	public ResponseEntity<Screen> deleteScreenDetails(long screenId);
	/**
	 * 
	 * @param screenId
	 * @return viewscreendetailsbyId
	 */

	public Screen viewScreenDetailsById(long screenId);
	/**
	 * 
	 * @return viewallscreendetails
	 */

	public List<Screen> viewAllScreenDetails();
	/**
	 * 
	 * @param theatre
	 * @return findbytheatre
	 */

	public List<Screen> findByTheatre(Theatre theatre);
}
