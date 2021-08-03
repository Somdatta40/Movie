package com.cg.mts.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mts.entity.Theatre;
import com.cg.mts.entity.Ticket;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket,Long>{

	/**
	 * 
	 * @param screenName
	 * @return details of ticket  by screen name
	 */
	public List<Ticket> findByScreenName(String screenName);
	/**
	 * 
	 * @param theatre
	 * @return details of ticket by theatre
	 */
	public List<Ticket> findByTheatre(Theatre theatre);
	
}