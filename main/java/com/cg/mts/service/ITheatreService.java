package com.cg.mts.service;

import java.util.List;


import org.springframework.http.ResponseEntity;

import com.cg.mts.entity.Theatre;


public interface ITheatreService {
	/**
	 * 
	 * @param theatre
	 * @return save theatre
	 */

	public Theatre saveTheatre(Theatre theatre);
	/**
	 * 
	 * @param theatre
	 * @param tId
	 * @return updatetheatre
	 */
	public Theatre updateTheatre(Theatre theatre, long tId) ;
	/**
	 * 
	 * @param tId
	 * @return deletetheatre
	 */
	public ResponseEntity<Theatre> deleteTheatre(long tId) ;
	/**
	 * 
	 * @param tId
	 * @return viewtheatrebyId
	 */
	public Theatre viewTheatreById(long tId) ;
	/**
	 * 
	 * @return viewalltheatre
	 */
	public List<Theatre> viewAllTheatre();
	/**
	 * 
	 * @param theatreName
	 * @return findbytheatreName
	 */
	public List<Theatre> findByTheatreName(String theatreName);
}