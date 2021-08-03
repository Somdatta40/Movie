package com.cg.mts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mts.entity.Movie;
import com.cg.mts.entity.Theatre;

@Repository
public interface IMovieRepository extends JpaRepository<Movie,Long>
{
	/**
	 * 
	 * @param theatre
	 * @return details of movie by theatre
	 */
	public List<Movie> findByTheatre(Theatre theatre);
}
