package com.cg.mts.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mts.entity.Theatre;

@Repository
public interface ITheatreRepository extends JpaRepository<Theatre,Long>{

	/**
	 * 
	 * @param theatreName
	 * @return details of theatre by theatreName
	 */
	public List<Theatre> findBytheatreName(String theatreName);
}