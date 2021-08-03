
package com.cg.mts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.mts.entity.Theatre;
import com.cg.mts.exception.TheatreNotFoundException;
import com.cg.mts.repository.ITheatreRepository;

@Service
public class TheatreService implements ITheatreService{

	@Autowired
	private ITheatreRepository theatreRepository;
	/**
	 * save theatre
	 */
	
	@Override
	public Theatre saveTheatre(Theatre theatre) {
		// TODO Auto-generated method stub
		return this.theatreRepository.save(theatre);
	}
	/**
	 * update theatre
	 */

	@Override
	public Theatre updateTheatre(Theatre theatre,long tId) {
		// TODO Auto-generated method stub
		Theatre existingTheatre=this.theatreRepository.findById(tId).orElseThrow(()-> new TheatreNotFoundException("Admin not found"+tId));
		existingTheatre.setTheatreContact(theatre.getTheatreContact());
		existingTheatre.setTheatreName(theatre.getTheatreName());
		return this.theatreRepository.save(existingTheatre);
	}
	/**
	 * view theatre by theatreId
	 */


	@Override
	public Theatre viewTheatreById(long tId) {
		// TODO Auto-generated method stub
		return this.theatreRepository.findById(tId).orElseThrow(()-> new TheatreNotFoundException("Theatre not found with id"+tId));
	}
	/**
	 * view all theatre
	 */

	@Override
	public List<Theatre> viewAllTheatre() {
		// TODO Auto-generated method stub
		return this.theatreRepository.findAll();
	}
	/**
	 * find by theatre name
	 */

	@Override
	public List<Theatre> findByTheatreName(String theatreName) {
		// TODO Auto-generated method stub
		return this.theatreRepository.findBytheatreName(theatreName);
	}
	/**
	 * delete theatre by theatreId
	 */

	@Override
	public ResponseEntity<Theatre> deleteTheatre(long tId) {
		// TODO Auto-generated method stub
		return null;
	}






}