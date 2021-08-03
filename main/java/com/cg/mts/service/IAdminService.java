package com.cg.mts.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.mts.entity.Admin;

public interface IAdminService {
	/**
	 * 
	 * @param admin
	 * @return admin
	 */

	public Admin saveAdmin(Admin admin);
	/**
	 * 
	 * @param admin
	 * @param adminId
	 * @return admin by adminId
	 */

	public Admin updateAdmin(Admin admin, long adminId);
	/**
	 * 
	 * @param adminId
	 * @return adminId
	 */

	public ResponseEntity<Admin> deleteAdmin(long adminId);
	/**
	 * 
	 * @param adminId
	 * @return adminId
	 */

	public Admin viewAdminById(long adminId);
	/**
	 * 
	 * @return all admins
	 */

	public List<Admin> viewAllAdmins();
	/**
	 * 
	 * @param adminName
	 * @return adminName
	 */

	public List<Admin> findByAdminName(String adminName);
}
