package com.cg.mts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mts.entity.Admin;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Long> {

	/**
	 * 
	 * @param adminName
	 * @return details of admin by adminName
	 */
	public List<Admin> findByAdminName(String adminName);
}
