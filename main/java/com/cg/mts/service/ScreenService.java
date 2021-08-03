package com.cg.mts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.mts.entity.Screen;
import com.cg.mts.entity.Seat;
import com.cg.mts.entity.Theatre;
import com.cg.mts.exception.ScreenDetailsNotFoundException;
import com.cg.mts.repository.IScreenRepository;

@Service
public class ScreenService implements IScreenService {

	@Autowired
	private IScreenRepository screenRepository;

	/**
	 * add screen details
	 */
	@Override
	public Screen addScreenDetails(Screen screen) {
		return this.screenRepository.save(screen);
	}

	/**
	 * update screen details
	 */
	@Override
	public Screen updateScreenDetails(Screen screen, long screenId) {
		Screen existingScreen = this.screenRepository.findById(screenId)
				.orElseThrow(() -> new ScreenDetailsNotFoundException("screen details not found" + screenId));
		existingScreen.setScreenName(screen.getScreenName());
		return this.screenRepository.save(existingScreen);
	}

	/**
	 * delete screen details
	 */
	@Override
	public ResponseEntity<Screen> deleteScreenDetails(long screenId) {
		Screen existingScreen = this.screenRepository.findById(screenId)
				.orElseThrow(() -> new ScreenDetailsNotFoundException("Screen details not found" + screenId));
		this.screenRepository.delete(existingScreen);
		return ResponseEntity.ok().build();
	}

	/**
	 * view screen details by screenId
	 */
	@Override
	public Screen viewScreenDetailsById(long screenId) {
		return this.screenRepository.findById(screenId)
				.orElseThrow(() -> new ScreenDetailsNotFoundException("Screen details not found with id" + screenId));
	}

	/**
	 * view all screen details
	 */
	@Override
	public List<Screen> viewAllScreenDetails() {
		return this.screenRepository.findAll();
	}

	/**
	 * view screen details by theatre
	 */
	@Override
	public List<Screen> findByTheatre(Theatre theatre) {
		// TODO Auto-generated method stub
		return this.screenRepository.findByTheatre(theatre);
	}

}
