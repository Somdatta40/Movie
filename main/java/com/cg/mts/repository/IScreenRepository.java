package com.cg.mts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.mts.entity.Screen;
import com.cg.mts.entity.Seat;
import com.cg.mts.entity.Theatre;

public interface IScreenRepository extends JpaRepository<Screen, Long> {
	/**
	 * 
	 * @param theatre
	 * @return details of screen by theatre
	 */
	public List<Screen> findByTheatre(Theatre theatre);
}
